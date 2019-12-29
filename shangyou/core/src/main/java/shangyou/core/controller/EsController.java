package shangyou.core.controller;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import shangyou.core.common.Utility;
import shangyou.core.configure.EsConfigure;
import shangyou.core.model.BaseStamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class EsController {

    private static final String STAMP_INDEX_NAME = "stamp";

    @Autowired
    private BaseStampController baseStampController;

    private EsConfigure configure;
    private RestHighLevelClient client;

    @Autowired
    public EsController(EsConfigure configure) {
        this.configure = configure;

        String[] hosts = this.configure.getHosts().split(",");
        HttpHost[] httpHosts = Arrays.stream(hosts).map(host -> {
            String[] hostParts = host.split(":");
            String hostName = hostParts[0];
            int port = Integer.parseInt(hostParts[1]);
            return new HttpHost(hostName, port, "http");
        }).toArray(HttpHost[]::new);

        RestClientBuilder builder = RestClient.builder(httpHosts);
        this.client = new RestHighLevelClient(builder);
    }

    private List<String> rawSearch(String query, int type, int year, int offset, int size) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(query)) {
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(query)
                    .field("name", 2)
                    .field("background");
            multiMatchQueryBuilder.type(MultiMatchQueryBuilder.Type.BEST_FIELDS);
            boolQueryBuilder.must(multiMatchQueryBuilder);
        }
        if (year != 0) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("year", year));
        }
        if (type != 0) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("type", type));
        }

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQueryBuilder);
        sourceBuilder.from(offset);
        sourceBuilder.size(size);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(STAMP_INDEX_NAME);
        searchRequest.source(sourceBuilder);

        List<String> results = new ArrayList<>();
        SearchResponse response;
        try {
            response = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("查询邮票失败", e);
            return results;
        }
        SearchHits hits = response.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            results.add((String)hit.getSourceAsMap().get("stid"));
        }
        log.info("es引擎返回:{}", Utility.objToJsonString(searchHits));
        return results;
    }

    public List<BaseStamp> search(String query, int type, int year, int offset, int size) {
        List<String> stids = this.rawSearch(query, type, year, offset, size);
        if (CollectionUtils.isEmpty(stids)) {
            return Lists.newArrayList();
        }

        List<BaseStamp> ret = Lists.newArrayList();
        for (String stid : stids) {
            BaseStamp baseStamp = baseStampController.queryBaseStampByStampId(stid);
            if (baseStamp != null) {
                ret.add(baseStamp);
            }
        }
        return ret;
    }

}

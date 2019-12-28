package shangyou.core.data.redis;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class SimStampRedis {

    private final String KEY_PREFIX = "RE_";

    @Autowired
    private StringRedisTemplate redisTemplate;

    public List<Pair<String, BigDecimal>> getSimilar(String stid) {
        String key = KEY_PREFIX + stid;
        String value = redisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        // 原数据存放格式: s6680cb9|0.21147858,s7a4a9e0|0.20947373,s4b20768
        List<Pair<String, BigDecimal>> ret = Lists.newArrayList();
        String[] similarStamp = value.split(",");
        for (String item : similarStamp) {
            String[] pair = item.split("\\|");
            if (pair.length >= 2) {
                String simStid = pair[0];
                BigDecimal score = new BigDecimal(pair[1]);
                ret.add(Pair.of(simStid, score));
            }
        }
        return ret;
    }

}

package shangyou.core.controller;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import shangyou.core.data.repo.SimStampRepo;
import shangyou.core.model.BaseStamp;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class RecomController extends BaseController {

    private static final BigDecimal MIN_SCORE = BigDecimal.valueOf(0.07);

    @Autowired
    private SimStampRepo simStampRepo;
    @Autowired
    private BaseStampController baseStampController;

    public List<BaseStamp> querySimilars(String stid, int size) {
        List<Pair<String, BigDecimal>> similars = simStampRepo.getSimilar(stid);
        if (CollectionUtils.isEmpty(similars)) {
            return Lists.newArrayList();
        }

        List<BaseStamp> ret = Lists.newArrayList();
        int n = 0;
        for (Pair<String, BigDecimal> pair : similars) {
            BigDecimal score = pair.getSecond();
            if (score.compareTo(MIN_SCORE) <= 0) {
                continue;
            }

            String similarStId = pair.getFirst();
            BaseStamp baseStamp = baseStampController.queryBaseStampByStampId(similarStId);
            if (baseStamp == null) {
                continue;
            }
            ret.add(baseStamp);
            n++;
            if (n >= size) {
                break;
            }
        }
        return ret;
    }

}

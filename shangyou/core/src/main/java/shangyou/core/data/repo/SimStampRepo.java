package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;
import shangyou.core.data.redis.SimStampRedis;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class SimStampRepo {

    @Autowired
    private SimStampRedis simStampRedis;

    public List<Pair<String, BigDecimal>> getSimilar(String stid) {
        return simStampRedis.getSimilar(stid);
    }

}

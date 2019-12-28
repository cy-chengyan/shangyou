package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class SimStampRepo {

    @Autowired
    private SimStampRepo simStampRepo;

    public List<Pair<String, BigDecimal>> getSimilar(String stid) {
        return simStampRepo.getSimilar(stid);
    }

}

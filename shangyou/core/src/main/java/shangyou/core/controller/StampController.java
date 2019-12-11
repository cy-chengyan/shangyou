package shangyou.core.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.repo.StampRepo;
import shangyou.core.model.Stamp;

import java.util.List;

@Controller
public class StampController {
    @Autowired
    private StampRepo stampRepo;
    public List<Stamp> queryStampByStampId(String stid) {
        return stampRepo.queryStampByStampId(stid);
    }
}

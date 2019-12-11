package shangyou.core.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.repo.BaseStampRepo;
import shangyou.core.model.BaseStamp;


@Controller
public class BaseStampController {
    @Autowired
    private BaseStampRepo baseStampRepo;
    public BaseStamp queryStampByStampId(String stid) {
        return baseStampRepo.queryStampByStampId(stid);
    }
}

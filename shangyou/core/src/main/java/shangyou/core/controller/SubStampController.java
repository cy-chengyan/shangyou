package shangyou.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.repo.SubStampRepo;
import shangyou.core.model.SubStamp;

import java.util.List;

@Controller
public class SubStampController {

    @Autowired
    private SubStampRepo subStampRepo;

    public List<SubStamp> queryByStampId(String stid) {
        return subStampRepo.queryByStampId(stid);
    }

}

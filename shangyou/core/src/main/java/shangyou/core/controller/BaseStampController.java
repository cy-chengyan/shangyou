package shangyou.core.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.common.ErrMsg;
import shangyou.core.data.repo.BaseStampRepo;
import shangyou.core.model.BaseStamp;


@Controller
public class BaseStampController extends BaseController {

    @Autowired
    private BaseStampRepo baseStampRepo;

    public BaseStamp queryBaseStampByStampId(String stid) {
        BaseStamp ret = baseStampRepo.queryStampByStampId(stid);
        if (ret == null) {
            setLastErrWithPredefined(ErrMsg.RC_NOT_FOUND);
        }
        return ret;
    }

}

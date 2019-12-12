package shangyou.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.common.ErrMsg;
import shangyou.core.data.repo.StampDetailRepo;
import shangyou.core.model.StampDetail;

@Controller
public class StampDetailController extends BaseController {

    @Autowired
    private StampDetailRepo stampDetailRepo;

    public StampDetail queryStampDetailByStampId(String stid) {
        StampDetail stampDetail = stampDetailRepo.queryStampByStid(stid);
        if (stampDetail == null) {
            setLastErrWithPredefined(ErrMsg.RC_NOT_FOUND);
        }
        return stampDetail;
    }

}

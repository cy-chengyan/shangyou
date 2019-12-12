package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.model.*;

import java.util.List;

@Controller
public class StampDetailRepo {

    @Autowired
    private BaseStampRepo baseStampRepo;
    @Autowired
    private SubStampRepo subStampRepo;

    public StampDetail queryStampByStid(String stid){

        BaseStamp baseStamp = baseStampRepo.queryStampByStampId(stid);
        if (baseStamp == null) {
            return null;
        }

        List<SubStamp> subStamps = subStampRepo.queryByStampId(stid);


        return StampDetail.builder()
                .baseStamp(baseStamp)
                .subStamps(subStamps)
                .build();
    }



}

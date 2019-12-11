package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.mapper.BaseStampMapper;
import shangyou.core.model.BaseStamp;


@Controller
public class BaseStampRepo {

    @Autowired
    private BaseStampMapper baseStampMapper;

    public BaseStamp queryStampByStampId(String stid) {
        BaseStamp stamp = baseStampMapper.queryStampByStid(stid);
        return  stamp;
    }
}

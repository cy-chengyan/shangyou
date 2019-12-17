package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import shangyou.core.data.mapper.BaseStampMapper;
import shangyou.core.model.BaseStamp;

import java.util.List;


@Repository
public class BaseStampRepo {

    @Autowired
    private BaseStampMapper baseStampMapper;

    public BaseStamp queryStampByStampId(String stid) {
        BaseStamp stamp = baseStampMapper.queryStampByStid(stid);
        return  stamp;
    }

    public List<BaseStamp> queryStamp(int type, int year, int offset, int size) {
        return baseStampMapper.queryStamp(type, year, offset, size);
    }

}

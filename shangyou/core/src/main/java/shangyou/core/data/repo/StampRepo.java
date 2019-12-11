package shangyou.core.data.repo;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.mapper.StampMapper;
import shangyou.core.model.Stamp;

import java.util.List;

@Controller
public class StampRepo {

    @Autowired
    private StampMapper stampMapper;
    public List<Stamp> queryStampByStampId(@Param("stid") String stid) {
        List<Stamp> stamps = stampMapper.queryStampByStampId(stid);
        return  stamps;
    }
}

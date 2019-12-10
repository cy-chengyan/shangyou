package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.mapper.SubStampMapper;
import shangyou.core.model.SubStamp;

import java.util.List;

@Controller
public class SubStampRepo {

    @Autowired
    private SubStampMapper subStampMapper;

    public List<SubStamp> queryByStampId(String stid) {
        List<SubStamp> subStamps = subStampMapper.queryByStampid(stid);
        if (subStamps != null) {
            subStamps.forEach(subStamp -> subStamp.setPicture("https://img.huanliu.club//tp/coffeesalon/icon/icon_bk_06.png"));
        }
        return subStamps;
    }

}

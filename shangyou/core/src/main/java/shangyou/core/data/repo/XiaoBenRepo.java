package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.mapper.XiaoBenMapper;
import shangyou.core.model.XiaoBen;

import java.util.List;

@Controller
public class XiaoBenRepo {
    @Autowired
    private XiaoBenMapper xiaoBenMapper;
    public List<XiaoBen> queryStampByStid(String stid){
        List<XiaoBen> xiaoBens = xiaoBenMapper.queryStampByStid(stid);
        return xiaoBens;
    }
}

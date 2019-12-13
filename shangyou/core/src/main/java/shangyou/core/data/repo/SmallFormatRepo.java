package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.mapper.SmallFormatMapper;
import shangyou.core.model.SmallFormat;

import java.util.List;

@Controller
public class SmallFormatRepo {
    @Autowired
    private SmallFormatMapper smallFormatMapper;
    public List<SmallFormat> queryStampByStid (String stid){
        List<SmallFormat> smallFormats = smallFormatMapper.queryStampByStid(stid);
        return  smallFormats;
    }
}

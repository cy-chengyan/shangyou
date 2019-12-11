package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.mapper.BigFormatMapper;
import shangyou.core.model.BigFormat;

import java.util.List;

@Controller
public class BigFormatRepo {

    @Autowired
    private BigFormatMapper bigFormatMapper;

    public List<BigFormat> queryStampByStid(String stid){
       List<BigFormat> bigFormats = bigFormatMapper.queryStampByStid(stid);
       return bigFormats;
    }
}

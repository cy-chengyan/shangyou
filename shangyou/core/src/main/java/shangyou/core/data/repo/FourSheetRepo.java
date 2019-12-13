package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.mapper.FourSheetMapper;
import shangyou.core.model.FourSheet;

import java.util.List;

@Controller
public class FourSheetRepo {
    @Autowired
    private FourSheetMapper fourSheetMapper;
    public List<FourSheet> queryStampByStid(String stid){
        List<FourSheet> fourSheets = fourSheetMapper.queryStampByStid(stid);
        return  fourSheets;
    }
}

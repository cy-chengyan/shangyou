package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.mapper.SmallSheetMaaper;
import shangyou.core.model.SmallSheet;

import java.util.List;

@Controller
public class SmallSheetRepo {
    @Autowired
    private SmallSheetMaaper smallSheetMaaper;
    public List<SmallSheet> queryStampByStid(String stid){
        List<SmallSheet> smallSheets = smallSheetMaaper.queryStampByStid(stid);
        return smallSheets;
    }
}

package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.mapper.DoubleSheetMapper;
import shangyou.core.model.DoubleSheet;

import java.util.List;

@Controller
public class DoubleSheetRepo {
    @Autowired
    private DoubleSheetMapper doubleSheetMapper;
    public List<DoubleSheet> queryStampByStid(String stid) {
        List<DoubleSheet> doubleSheets = doubleSheetMapper.queryStampByStid(stid);
        return doubleSheets;
    }

}

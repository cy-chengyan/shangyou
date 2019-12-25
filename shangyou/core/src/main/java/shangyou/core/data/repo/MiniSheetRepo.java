package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shangyou.core.data.mapper.MiniSheetMapper;
import shangyou.core.model.MiniSheet;

import java.util.List;

@Repository
public class MiniSheetRepo {
    @Autowired
    private MiniSheetMapper miniSheetMapper;
    public List<MiniSheet> queryStampByStid(String stid){
        List<MiniSheet> miniSheets = miniSheetMapper.queryStampByStid(stid);
        return miniSheets;
    }
}

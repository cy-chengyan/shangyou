package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.mapper.ZengSongMapper;
import shangyou.core.model.ZengSong;

import java.util.List;

@Controller
public class ZengSongRepo {
    @Autowired
    private ZengSongMapper zengSongMapper;
    public List<ZengSong> queryStampByStid(String stid){
        List<ZengSong> zengSongs = zengSongMapper.queryStampByStid(stid);
        return zengSongs;
    }
}

package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.mapper.*;
import shangyou.core.model.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StampDetailRepo {
    @Autowired
    private BaseStampMapper baseStampMapper;

    @Autowired
    private BigFormatMapper bigFormatMapper;

    @Autowired
    private DoubleSheetMapper doubleSheetMapper;

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private FourSheetMapper fourSheetMapper;

    @Autowired
    private MiniSheetMapper miniSheetMapper;

    @Autowired
    private SmallFormatMapper smallFormatMapper;

    @Autowired
    private SmallSheetMaaper smallSheetMaaper;

    @Autowired
    private SubStampMapper subStampMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private XiaoBenMapper xiaoBenMapper;

    @Autowired
    private ZengSongMapper zengSongMapper;



    public BaseStamp queryBaseStampByStid(String stid) {
        BaseStamp baseStamp = baseStampMapper.queryStampByStid(stid);
        return baseStamp;
    }

    public List<BigFormat> queryBigFormatByStid(String stid) {
        List<BigFormat> bigFormats = bigFormatMapper.queryStampByStid(stid);
        return bigFormats;
    }

    public List<DoubleSheet> queryDoubleSheetByStid(String stid) {
        List<DoubleSheet> doubleSheets = doubleSheetMapper.queryStampByStid(stid);
        return doubleSheets;
    }

    public List<Favorite> queryFavoriteByUid(String uid) {
        List<Favorite> favorites = favoriteMapper.queryStampByUid(uid);
        return favorites;
    }

    public List<FourSheet> queryFourSheetByStid(String stid) {
        List<FourSheet> fourSheets = fourSheetMapper.queryStampByStid(stid);
        return fourSheets;
    }

    public List<MiniSheet> queryMiniSheetByStid(String stid) {
        List<MiniSheet> miniSheets = miniSheetMapper.queryStampByStid(stid);
        return miniSheets;
    }

    public List<SmallFormat> querySmallFormatByStid(String stid) {
        List<SmallFormat> smallFormats = smallFormatMapper.queryStampByStid(stid);
        return smallFormats;
    }

    public List<SmallSheet> querySmallSheetByStid(String stid) {
        List<SmallSheet> smallSheets = smallSheetMaaper.queryStampByStid(stid);
        return smallSheets;
    }

    public List<SubStamp> querySubStampByStid(String stid) {
        List<SubStamp> subStamps = subStampMapper.queryByStampid(stid);
        return subStamps;
    }

    public User queryUserByUid(String uid){
        User user = userMapper.queryUserByUid(uid);
        return user;
    }

    public List<XiaoBen> queryXiaoBenByStid(String stid){
        List<XiaoBen> xiaoBens = xiaoBenMapper.queryStampByStid(stid);
        return xiaoBens;
    }

    public List<ZengSong> queryZengSongByStid(String stid){
        List<ZengSong> zengSongs = zengSongMapper.queryStampByStid(stid);
        return zengSongs;
    }





    public List<StampDetail> queryStampByStid(String stid){
        List<StampDetail> list = new ArrayList<>();
        BaseStamp baseStamp = queryBaseStampByStid(stid);

        return null;
    }



}

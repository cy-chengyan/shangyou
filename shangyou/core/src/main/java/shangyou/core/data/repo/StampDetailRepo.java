package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.model.*;

import java.util.List;

@Controller
public class StampDetailRepo {

    @Autowired
    private BaseStampRepo baseStampRepo;
    @Autowired
    private SubStampRepo subStampRepo;
    @Autowired
    private BigFormatRepo bigFormatRepo;
    @Autowired
    private DoubleSheetRepo doubleSheetRepo;
    @Autowired
    private FourSheetRepo fourSheetRepo;
    @Autowired
    private MiniSheetRepo miniSheetRepo;
    @Autowired
    private SmallFormatRepo smallFormatRepo;
    @Autowired
    private SmallSheetRepo smallSheetRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private XiaoBenRepo xiaoBenRepo;
    @Autowired
    private ZengSongRepo zengSongRepo;

    public StampDetail queryStampByStid(String stid){

        BaseStamp baseStamp = baseStampRepo.queryStampByStampId(stid);
        if (baseStamp == null) {
            return null;
        }

        List<SubStamp> subStamps = subStampRepo.queryByStampId(stid);

        List<BigFormat> bigFormats = bigFormatRepo.queryStampByStid(stid);

        List<DoubleSheet> doubleSheets = doubleSheetRepo.queryStampByStid(stid);

        List<FourSheet> fourSheets = fourSheetRepo.queryStampByStid(stid);

        List<MiniSheet> miniSheets = miniSheetRepo.queryStampByStid(stid);

        List<SmallFormat> smallFormats = smallFormatRepo.queryStampByStid(stid);

        List<SmallSheet> smallSheets = smallSheetRepo.queryStampByStid(stid);

        List<XiaoBen> xiaoBens = xiaoBenRepo.queryStampByStid(stid);

        List<ZengSong> zengSongs = zengSongRepo.queryStampByStid(stid);


        return StampDetail.builder()
                .baseStamp(baseStamp)
                .subStamps(subStamps)
                .bigFormats(bigFormats)
                .doubleSheets(doubleSheets)
                .fourSheets(fourSheets)
                .miniSheets(miniSheets)
                .smallFormats(smallFormats)
                .smallSheets(smallSheets)
                .xiaoBens(xiaoBens)
                .zengSongs(zengSongs)
                .build();
    }



}

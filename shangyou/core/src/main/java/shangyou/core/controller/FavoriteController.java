package shangyou.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import shangyou.core.common.ErrMsg;
import shangyou.core.data.repo.BaseStampRepo;
import shangyou.core.data.repo.FavoriteRepo;
import shangyou.core.model.BaseStamp;
import shangyou.core.model.Favorite;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Component
public class FavoriteController extends BaseController {
    @Autowired
    private FavoriteRepo favoriteRepo;
    @Autowired
    private BaseStampRepo baseStampRepo;

    public Favorite userFavorite(String uid, String stid, int status) {
        Favorite favorite = favoriteRepo.queryStampByUidAndStid(uid, stid);
        // 如果记录不存在
        if (ObjectUtils.isEmpty(favorite)) {
            if (status == 1) {
                favorite = Favorite.builder()
                        .faid(UUID.randomUUID().toString().substring(0, 8))
                        .uid(uid)
                        .stid(stid)
                        .status(status)
                        .createdAt(ZonedDateTime.now().toEpochSecond())
                        .build();
                favoriteRepo.userFavorite(favorite);
                return favorite;
            } else {
                setLastErrWithPredefined(ErrMsg.RC_INITIAL_STATE_ERR);
                return null;
            }
        }

        // 如果记录已存在，并且状态也一样，则直接返回
        if (favorite.getStatus() == status) {
            return favorite;
        }

        // 如果记录已存在，状态不一样，则更新
        favoriteRepo.updateStampStatusByUidAndStid(uid, stid, status);
        favorite.setStatus(status);
        return favorite;
    }

    public List<BaseStamp> queryFavoriteStamp(String uid, int offset, int size) {
        List<BaseStamp> baseStamps = favoriteRepo.queryFavoriteStamp(uid, offset, size);
        if (baseStamps == null) {
            return null;
        }
        baseStamps.forEach(baseStamp -> baseStampRepo.fillFieldPictures(baseStamp));
        return baseStamps;
    }

    public List<String> queryStidByUid(String uid) {
        List<String> stids = favoriteRepo.queryStidByUid(uid);
        if (stids == null) {
            return null;
        }
        return stids;
    }

}

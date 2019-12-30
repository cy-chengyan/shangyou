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

    public Favorite userFavorite(String uid, String stid, int status) {
        Favorite favorite = favoriteRepo.queryStampByStid(stid);
        if (ObjectUtils.isEmpty(favorite) && status == 1) {
            favorite = Favorite.builder()
                    .faid(UUID.randomUUID().toString().substring(0, 8))
                    .uid(uid)
                    .stid(stid)
                    .status(status)
                    .createdAt(ZonedDateTime.now().toEpochSecond())
                    .build();
            favoriteRepo.userFavorite(favorite);
            return favorite;
        }
        if (ObjectUtils.isEmpty(favorite) && status == 2) {
            setLastErrWithPredefined(ErrMsg.RC_INITIAL_STATE_ERR);
            return null;
        }

        if (favorite.getStatus() == status) {
            return favorite;
        }
        favoriteRepo.updateStampStatusByStid(status, stid);
        favorite = favoriteRepo.queryStampByStid(stid);
        return favorite;

    }

    public List<BaseStamp> queryFavoriteStamp(String uid, int offset, int size) {
        List<BaseStamp> baseStamps = favoriteRepo.queryFavoriteStamp(uid, offset, size);
        if (baseStamps == null) {
            return null;
        }
        return baseStamps;
    }

}

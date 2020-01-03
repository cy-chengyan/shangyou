package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.mapper.FavoriteMapper;
import shangyou.core.model.BaseStamp;
import shangyou.core.model.Favorite;

import java.util.List;

@Controller
public class FavoriteRepo {

    @Autowired
    private FavoriteMapper favoriteMapper;

    public List<Favorite> queryStampByUid(String uid) {
        List<Favorite> favorites = favoriteMapper.queryStampByUid(uid);
        return favorites;
    }

    public void userFavorite(Favorite favorite) {
        favoriteMapper.userFavorite(favorite);
    }

    public Favorite queryStampByUidAndStid(String uid, String stid) {
        return favoriteMapper.queryStampByUidAndStid(uid, stid);
    }

    public int updateStampStatusByUidAndStid(String uid, String stid, int status) {
        return favoriteMapper.updateStampStatusByUidAndStid(uid, stid, status);
    }

    public List<BaseStamp> queryFavoriteStamp(String uid, int offset, int size) {
        return favoriteMapper.queryFavoriteStamp(uid, offset, size);
    }

    public List<String> queryStidByUid (String uid) {
        return favoriteMapper.queryStidByUid(uid);
    }

}

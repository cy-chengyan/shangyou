package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.mapper.FavoriteMapper;
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

    public Favorite queryStampByStid(String stid) {
     return favoriteMapper.queryStampByStid(stid);
    }

    public int updateStampStatusByStid(int status, String stid) {
      return favoriteMapper.updateStampStatusByStid(status, stid);
    }
}

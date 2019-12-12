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
}

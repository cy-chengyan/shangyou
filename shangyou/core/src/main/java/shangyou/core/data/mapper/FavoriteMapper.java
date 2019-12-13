package shangyou.core.data.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.Favorite;

import java.util.List;


@Repository
@Mapper
public interface FavoriteMapper {
    @Select({
            "<script>",
            "SELECT faid, uid, stid, created_at, updated_at, status FROM t_favorite WHERE uid = #{uid}",
            "</script>"
    })
    @Results({
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "update_at")
    })
    List<Favorite> queryStampByUid(@Param("uid")String uid);
}

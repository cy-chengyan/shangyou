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
            "SELECT faid, uid, stid, created_at, status FROM t_favorite WHERE uid = #{uid}",
            "</script>"
    })
    @Results({
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "update_at")
    })
    List<Favorite> queryStampByUid(@Param("uid")String uid);

    @Insert({
            "<script>",
            "INSERT INTO t_favorite (faid, uid, stid, status, created_at)",
            "VALUES (#{faid}, #{uid}, #{stid}, #{status}, #{createdAt})",
            "</script>"
    })
    int userFavorite(Favorite favorite);

    @Select({
            "<script>",
            "SELECT faid, uid, stid, status, created_at FROM t_favorite WHERE stid = #{stid}",
            "</script>"
    })
    @Results({
            @Result(property = "createdAt", column = "created_at")
    })
    Favorite queryStampByStid(@Param("stid")String stid);

    @Update({
            "<script>",
            "UPDATE t_favorite SET status=#{status} WHERE stid=#{stid}",
            "</script>"
    })
    int updateStampStatusByStid(@Param("status")int status, @Param("stid")String stid);
}

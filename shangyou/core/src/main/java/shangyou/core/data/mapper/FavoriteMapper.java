package shangyou.core.data.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.BaseStamp;
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

    @Select({
            "<script>",
            "SELECT faid, uid, stid, status, created_at FROM t_favorite WHERE uid = #{uid}",
            "</script>"
    })
    @Results({
            @Result(property = "createdAt", column = "created_at")
    })
    List<Favorite> queryFavoriteByUid(@Param("uid")String uid);

    @Select({
            "<script>",
            "select t.stid, `type`, year, `name`, countryid, `number`, issued_date, joint_issue, size, chikong, format, fanwei, designer,",
            " editor, carve, side_design, draw, shoot, printing_house, background, picture FROM",
            " t_stamp as t join t_favorite as f on t.stid = f.stid WHERE uid = #{uid} AND f.status = 1",
            " ORDER BY f.created_at DESC",
            "<if test=\"offset >= 0 and size > 0\"> LIMIT ${offset}, #{size}</if>",
            "</script>"
    })
    @Results({
            @Result(property = "issuedDate", column = "issued_date"),
            @Result(property = "jointIssue", column = "joint_issue"),
            @Result(property = "sideDesign", column = "side_design"),
            @Result(property = "printingHouse", column = "printing_house")
    })
    List<BaseStamp> queryFavoriteStamp(@Param("uid")String uid, int offset, int size);
}

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
            "SELECT faid, uid, stid, status, created_at FROM t_favorite WHERE uid = #{uid} AND stid = #{stid}",
            "</script>"
    })
    @Results({
            @Result(property = "createdAt", column = "created_at")
    })
    Favorite queryStampByUidAndStid(@Param("uid")String uid, @Param("stid")String stid);

    @Update({
            "<script>",
            "UPDATE t_favorite SET status = #{status} WHERE uid = #{uid} AND stid = #{stid}",
            "</script>"
    })
    int updateStampStatusByUidAndStid(@Param("uid")String uid, @Param("stid")String stid, @Param("status")int status);

    @Select({
            "<script>",
            "SELECT t.stid, `type`, year, `name`, countryid, `number`, issued_date, joint_issue, size, chikong, format, fanwei, designer,",
            " editor, carve, side_design, draw, shoot, printing_house, background, picture FROM",
            " t_stamp as t join t_favorite as f ON t.stid = f.stid WHERE uid = #{uid} AND f.status = 1",
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

    @Select({
            "<script>",
            "SELECT stid FROM t_favorite WHERE uid = #{uid} AND status = 1",
            "</script>"
    })
    List<String> queryStidByUid(@Param("uid")String uid);

}

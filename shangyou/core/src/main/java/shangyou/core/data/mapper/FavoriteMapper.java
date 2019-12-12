package shangyou.core.data.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.Favorite;

import java.util.List;

/*
        `faid` char(8) PRIMARY KEY,
       `uid` char(8) NOT NULL COMMENT '用户id',
       `stid` char(8) NOT NULL COMMENT '邮票id',
       `created_at` bigint(20) NOT NULL COMMENT '创建时间',
       `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP() COMMENT '更新时间',
       `status` tinyint(1) NOT NULL COMMENT '1收藏,2未收藏'
 */
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

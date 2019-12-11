package shangyou.core.data.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.User;


/*
        `uid` char(8) PRIMARY KEY COMMENT '用户id',
       `mobile_number` varchar(32) NOT NULL COMMENT '手机号码',
       `nickname` varchar(32) NOT NULL COMMENT '昵称',
       `gender` tinyint(1) NOT NULL COMMENT '性别,1男,2女,3未知',
       `created_at` bigint(20) NOT NULL COMMENT '创建时间',
       `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP() COMMENT '更新时间'
 */
@Repository
@Mapper
public interface UserMapper {
    @Select({
            "<script>",
            "SELECT uid, mobile_number, nickname, gender, created_at, update_at FROM t_user WHERE uid = #{uid}",
            "</script>"
    })
    @Results({
            @Result(property = "mobileNumber", column = "mobile_number"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updateAt", column = "update_at")
    })

    User queryUserByUid(@Param("uid")String uid);


}

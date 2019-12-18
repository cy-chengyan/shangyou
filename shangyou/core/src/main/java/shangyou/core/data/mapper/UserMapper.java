package shangyou.core.data.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.User;



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

    @Select({
            "<script>",
            "SELECT uid, mobile_number, nickname, gender FROM t_user WHERE mobile_number = #{mobileNumber}",
            "</script>"
    })
    @Results({
            @Result(property = "mobileNumber", column = "mobile_number"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updateAt", column = "update_at")
    })
    User queryUserByPhone(@Param("mobileNumber")String mobileNumber);

    @Select({
            "<script>",
            "SELECT uid, mobile_number, nickname, gender FROM t_user WHERE nickname = #{nickname}",
            "</script>"
    })
    @Results({
            @Result(property = "mobileNumber", column = "mobile_number"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updateAt", column = "update_at")
    })
    User queryUserByNickname(@Param("nickname")String nickname);


    @Insert({
            "<script>",
            "INSERT INTO t_user (uid, mobile_number, nickname, gender, created_at)",
            "VALUES (#{uid}, #{mobileNumber}, #{nickname}, #{gender}, #{createdAt})",
            "</script>"
    })
    int insertUser(User user);

}




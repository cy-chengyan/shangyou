package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.mapper.UserMapper;
import shangyou.core.model.User;

import java.time.ZonedDateTime;
import java.util.UUID;

@Controller
public class UserRepo {

    @Autowired
    private UserMapper userMapper;

    public User queryUserByUid (String uid){
        User user = userMapper.queryUserByUid(uid);
        return  user;
    }

    public User queryUserByPhone(String phone){
        User user = userMapper.queryUserByPhone(phone);
        return user;
    }

    public void userRegistered(String phone, String nickname){
        User user = User.builder()
                .uid(UUID.randomUUID().toString().substring(0, 8))
                .gender(0)
                .createdAt(ZonedDateTime.now().toEpochSecond())
                .mobileNumber(phone)
                .nickname(nickname)
                .build();
        userMapper.insterUser(user);
    }

}

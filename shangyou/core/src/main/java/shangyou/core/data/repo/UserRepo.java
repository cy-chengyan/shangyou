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

    public User queryUserByPhone(String mobileNumber){
        User user = userMapper.queryUserByPhone(mobileNumber);
        return user;
    }

    public void userRegistered(String mobileNumber, String nickname){
        User user = User.builder()
                .uid(UUID.randomUUID().toString().substring(0, 8))
                .gender(0)
                .createdAt(ZonedDateTime.now().toEpochSecond())
                .mobileNumber(mobileNumber)
                .nickname(nickname)
                .build();
        userMapper.insterUser(user);
    }

}

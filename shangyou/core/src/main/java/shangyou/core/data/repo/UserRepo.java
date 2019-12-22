package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import shangyou.core.data.mapper.UserMapper;
import shangyou.core.model.User;


@Controller
public class UserRepo {

    @Autowired
    private UserMapper userMapper;

    public User queryUserByUid (String uid){
        User user = userMapper.queryUserByUid(uid);
        return  user;
    }

    public User queryUserByMobileNumber(String mobileNumber){
        User user = userMapper.queryUserByMobileNumber(mobileNumber);
        return user;
    }

    public User queryUserByNickname(String nickname) {
        User user = userMapper.queryUserByNickname(nickname);
        return user;
    }

    public void userRegister(User user){
        userMapper.insertUser(user);
    }

    public int userUpdate(User user) {
        return userMapper.updateGender(user);
    }

}

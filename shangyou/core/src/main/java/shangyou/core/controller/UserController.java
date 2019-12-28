package shangyou.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shangyou.core.common.ErrMsg;
import shangyou.core.data.repo.UserRepo;
import shangyou.core.model.User;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class UserController extends BaseController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CheckCodeController checkCodeController;

    public User queryUserByUid(String uid){
       User user = userRepo.queryUserByUid(uid);
        return user;
    }

    public User userRegOrLogin(String mobileNumber, String checkCode) {
        if (!checkCodeController.isMatched(mobileNumber, checkCode)) {
            setLastErrWithPredefined(ErrMsg.RC_CHECK_CODE_ERR);
            return null;
        }

        // 如果已存在，返回存在的用户信息
        User user = userRepo.queryUserByMobileNumber(mobileNumber);
        if (user != null) {
            return user;
        }

        // 如果不存在，则添加新用户
        user = User.builder()
                .uid(UUID.randomUUID().toString().substring(0, 8))
                .createdAt(ZonedDateTime.now().toEpochSecond())
                .mobileNumber(mobileNumber)
                .build();
        userRepo.userRegister(user);
        return user;
    }

    public User queryUserByPhone(String mobileNumber) {
        User user = userRepo.queryUserByMobileNumber(mobileNumber);
        return user;
    }

    public User queryUserByNickname(String nickname) {
        User user = userRepo.queryUserByNickname(nickname);
        return user;
    }

    public User userUpdate(String uid, Map<String, Object> map) {
        User user = userRepo.queryUserByUid(uid);
        if (user == null) {
            setLastErrWithPredefined(ErrMsg.RC_NOT_FOUND_USER);
            return null;
        }

        Object gender = map.get("gender");
        if (gender != null) {
            user.setGender(Integer.parseInt(gender.toString()));
        }
        String avatar = (String)map.get("avatar");
        if (avatar != null) {
            user.setAvatar(avatar);
        }
        String nickname = (String)map.get("nickname");
        if (nickname != null) {
            user.setNickname(nickname);
        }

        userRepo.userUpdate(user);
        return user;
    }

}

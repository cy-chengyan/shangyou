package shangyou.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import shangyou.core.common.ErrMsg;
import shangyou.core.data.repo.UserRepo;
import shangyou.core.model.User;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

import static shangyou.core.common.Constant.MOBILE_NUMBER_REGEX;

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

    public User userUpdate(Map<String, Object> map) {
        String uid = (String)map.get("uid");
        User user = userRepo.queryUserByUid(uid);
        int gender = (int)map.get("gender");
        String avatar = (String)map.get("avatar");
        String mobileNumber = (String)map.get("mobile_number");
        if (user.getMobileNumber().equals(mobileNumber) && user.getGender() == gender && user.getAvatar().equals(avatar)) {
            return user;
        }

        user = userRepo.queryUserByMobileNumber(mobileNumber);
        if (!ObjectUtils.isEmpty(user)) {
            setLastErrWithPredefined(ErrMsg.RC_MOBILE_NUMBER_ALREADY_EXISTS);
            return null;
        }
        user = userRepo.queryUserByUid(uid);
        if (!StringUtils.isEmpty(gender) && (gender == 1 || gender == 2 || gender == 3)) {
            user.setGender(gender);
        }
        if (!StringUtils.isEmpty(avatar)) {
            user.setAvatar(avatar);
        }
        if (!StringUtils.isEmpty(mobileNumber) && mobileNumber.matches(MOBILE_NUMBER_REGEX)) {
            user.setMobileNumber(mobileNumber);
        }
        userRepo.userUpdate(user);
        user = userRepo.queryUserByUid(uid);
        return user;
    }

}

package shangyou.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import shangyou.core.common.ErrMsg;
import shangyou.core.data.repo.UserRepo;
import shangyou.core.model.User;

import java.time.ZonedDateTime;
import java.util.UUID;

@Controller
public class UserController extends BaseController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CheckCodeController checkCodeController;

    public User queryUserByUid(String uid){
       User user = userRepo.queryUserByUid(uid);
        return user;
    }

    public User userLogin(String mobileNumber, String checkCode) {
        if (!checkCodeController.isMatched(mobileNumber, checkCode)) {
            setLastErrWithPredefined(ErrMsg.RC_CHECK_CODE_ERR);
            return null;
        }

        User user = userRepo.queryUserByPhone(mobileNumber);
        if (StringUtils.isEmpty(user)) {
            setLastErrWithPredefined(ErrMsg.RC_NOT_FOUND_USER);
            return null;
        }
        return user;
    }

    public User queryUserByPhone(String phone) {
        User user = userRepo.queryUserByPhone(phone);
        return user;
    }

    public User queryUserByNickname(String nickname) {
        User user = userRepo.queryUserByNickname(nickname);
        return user;
    }

    public User userRegister(String mobileNumber, String nickname, String checkCode, int gender) {
        if (!checkCodeController.isMatched(mobileNumber, checkCode)) {
            setLastErrWithPredefined(ErrMsg.RC_CHECK_CODE_ERR);
            return null;
        }

        User user = this.queryUserByPhone(mobileNumber);
        if (!ObjectUtils.isEmpty(user)) {
            setLastErrWithPredefined(ErrMsg.RC_USER_ALREADY_EXISTS);
            return null;
        }

        user = this.queryUserByNickname(nickname);
        if (!ObjectUtils.isEmpty(user)) {
            setLastErrWithPredefined(ErrMsg.RC_NICKNAME_USED);
            return null;
        }


        user = User.builder()
                .uid(UUID.randomUUID().toString().substring(0, 8))
                .gender(gender)
                .createdAt(ZonedDateTime.now().toEpochSecond())
                .mobileNumber(mobileNumber)
                .nickname(nickname)
                .build();
        userRepo.userRegister(user);
        return user;
    }

}

package shangyou.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import shangyou.core.common.ErrMsg;
import shangyou.core.data.repo.UserRepo;
import shangyou.core.model.User;

@Controller
public class UserController extends BaseController {

    @Autowired
    private UserRepo userRepo;

    public User queryUserByUid(String uid){
       User user = userRepo.queryUserByUid(uid);
        return user;
    }

    public User userLogin(String phone, String checkCode) {
        if ("2222".equals(checkCode)) {
            User user = userRepo.queryUserByPhone(phone);
            if (StringUtils.isEmpty(user)) {
                setLastErrWithPredefined(ErrMsg.RC_NOT_FOUND_USER);
                return null;
            }
            return user;
        }else {
            setLastErrWithPredefined(ErrMsg.RC_CHECK_CODE_ERR);
            return null;
        }

    }

    public User queryUserByPhone(String phone) {
        User user = userRepo.queryUserByPhone(phone);
        return user;
    }

    public void userRegistered(String phone, String nickname) {
        userRepo.userRegistered(phone, nickname);

    }
}

package shangyou.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    public void userLogin(String phone, String checkCode){
        if ("2222".equals(checkCode)){
            setLastErrWithPredefined(ErrMsg.RC_OK);
            return;
        }else {
            setLastErrWithPredefined(ErrMsg.RC_CHECK_CODE_ERR);
            return;
        }

    }
}

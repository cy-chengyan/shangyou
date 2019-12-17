package shangyou.api.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import shangyou.api.model.SApiRequest;
import shangyou.api.model.SApiResponse;
import shangyou.api.model.req.UserLoginRequestData;
import shangyou.api.model.req.UserRegisteredRequestData;
import shangyou.core.common.ErrMsg;
import shangyou.core.controller.UserController;
import shangyou.core.model.User;


import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
@Api(value = "User", tags = "用户")
public class SyUserController {


    @Autowired
    private UserController userController;


    @ApiOperation(value = "用户登录", notes = "根据验证码验证用户登录结果", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public SApiResponse<User> userLogin(@RequestBody @Valid SApiRequest<UserLoginRequestData> request) {
        UserLoginRequestData requestData = request.getData();
        String phone = requestData.getPhone();
        String checkCode = requestData.getCheckCode();
        User user = userController.userLogin(phone, checkCode);
        if (StringUtils.isEmpty(user)) {
            return new SApiResponse<>(userController.getLastErrCode(), userController.getLastErrMsg());
        }
        return new SApiResponse<>(ErrMsg.RC_OK, user);

    }


    @ApiOperation(value = "用户注册", notes = "根据验证码验证用户注册结果", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/reg", method = {RequestMethod.POST})
    public SApiResponse<User> UserRegistered(@RequestBody @Valid SApiRequest<UserRegisteredRequestData> request) {
        UserRegisteredRequestData registeredRequestData = request.getData();
        String phone = registeredRequestData.getPhone();
        String checkCode = registeredRequestData.getCheckCode();
        String nickname = registeredRequestData.getNickname();
        if ("2222".equals(checkCode)) {
            User user = userController.queryUserByPhone(phone);
            if (ObjectUtils.isEmpty(user)) {
                userController.userRegistered(phone, nickname);
                return new SApiResponse<>(ErrMsg.RC_OK, user);
            }
            return new SApiResponse<>(ErrMsg.RC_USER_ALREADY_EXISTS);
        }
        return new SApiResponse<>(ErrMsg.RC_CHECK_CODE_ERR);

    }



}

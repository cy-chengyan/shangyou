package shangyou.api.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shangyou.api.misc.ApiUtility;
import shangyou.api.model.SApiRequest;
import shangyou.api.model.SApiResponse;
import shangyou.api.model.req.SendCheckCodeData;
import shangyou.api.model.req.MobileNumberAndCheckCodeRequestData;
import shangyou.api.model.req.UserRegisteredRequestData;
import shangyou.api.model.res.LoginOrRegResponseData;
import shangyou.core.common.ErrMsg;
import shangyou.core.controller.CheckCodeController;
import shangyou.core.controller.UserController;
import shangyou.core.model.User;


import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
@Api(value = "User", tags = "用户")
@Slf4j
public class SyUserController {

    @Autowired
    private UserController userController;
    @Autowired
    private CheckCodeController checkCodeController;

    @ApiOperation(value = "用户登录", notes = "根据验证码验证用户登录结果", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public SApiResponse<LoginOrRegResponseData> userLogin(@RequestBody @Valid SApiRequest<MobileNumberAndCheckCodeRequestData> request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new SApiResponse<>(ErrMsg.RC_MISS_PARAM, bindingResult.getFieldError().getDefaultMessage());
        }
        MobileNumberAndCheckCodeRequestData requestData = request.getData();
        String mobileNumber = requestData.getMobileNumber();
        String checkCode = requestData.getCheckCode();
        User user = userController.userLogin(mobileNumber, checkCode);
        if (StringUtils.isEmpty(user)) {
            return new SApiResponse<>(userController.getLastErrCode(), userController.getLastErrMsg());
        }

        LoginOrRegResponseData loginOrRegResponseData = LoginOrRegResponseData.builder()
                .user(user)
                .loginInfo(ApiUtility.generalLoginInfoWithUser(user))
                .build();
        return new SApiResponse<>(ErrMsg.RC_OK, loginOrRegResponseData);
    }

    @ApiOperation(value = "用户注册", notes = "根据验证码验证用户注册结果", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/reg", method = {RequestMethod.POST})
    public SApiResponse<LoginOrRegResponseData> userRegistered(@RequestBody @Valid SApiRequest<UserRegisteredRequestData> request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new SApiResponse<>(ErrMsg.RC_MISS_PARAM, bindingResult.getFieldError().getDefaultMessage());
        }
        UserRegisteredRequestData registeredRequestData = request.getData();
        String mobileNumber = registeredRequestData.getMobileNumber();
        String checkCode = registeredRequestData.getCheckCode();
        String nickname = registeredRequestData.getNickname();
        User user = userController.userRegister(mobileNumber, nickname, checkCode);
        if (user == null) {
            return new SApiResponse<>(userController.getLastErrCode(), userController.getLastErrMsg());
        }

        LoginOrRegResponseData loginOrRegResponseData = LoginOrRegResponseData.builder()
                .user(user)
                .loginInfo(ApiUtility.generalLoginInfoWithUser(user))
                .build();
        return new SApiResponse<>(ErrMsg.RC_OK, loginOrRegResponseData);
    }

    @ApiOperation(value = "发送验证码", notes = "根据手机号给用户发送验证码", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/cc/send", method = {RequestMethod.POST})
    public SApiResponse sendCheckCode(@RequestBody @Valid SApiRequest<SendCheckCodeData> request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new SApiResponse(ErrMsg.RC_MISS_PARAM, bindingResult.getFieldError().getDefaultMessage());
        }
        SendCheckCodeData sendCheckCodeData = request.getData();
        String mobileNumber = sendCheckCodeData.getMobileNumber();
        if (!checkCodeController.sendCheckCode(mobileNumber)) {
            return new SApiResponse(checkCodeController.getLastErrCode(), checkCodeController.getLastErrMsg());
        }
        return new SApiResponse(ErrMsg.RC_OK);
    }

    @ApiOperation(value = "校对验证码", notes = "根据用户输入的验证码进行比对验证", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/cc/verify", method = {RequestMethod.POST})
    public SApiResponse verifyCheckCode(@RequestBody @Valid SApiRequest<MobileNumberAndCheckCodeRequestData> request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new SApiResponse(ErrMsg.RC_MISS_PARAM, bindingResult.getFieldError().getDefaultMessage());
        }
        MobileNumberAndCheckCodeRequestData verificationCheckCodeData = request.getData();
        String mobileNumber = verificationCheckCodeData.getMobileNumber();
        String checkCode = verificationCheckCodeData.getCheckCode();
        if (!checkCodeController.isMatched(mobileNumber, checkCode)) {
            return new SApiResponse(checkCodeController.getLastErrCode(), checkCodeController.getLastErrMsg());
        }
        return new SApiResponse(ErrMsg.RC_OK);
    }

}

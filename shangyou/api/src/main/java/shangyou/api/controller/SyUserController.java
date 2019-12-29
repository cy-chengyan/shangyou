package shangyou.api.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shangyou.api.misc.ApiUtility;
import shangyou.api.model.LoginInfo;
import shangyou.api.model.SApiRequest;
import shangyou.api.model.SApiResponse;
import shangyou.api.model.req.*;
import shangyou.api.model.res.LoginOrRegResponseData;
import shangyou.core.common.ErrMsg;
import shangyou.core.controller.*;
import shangyou.core.model.BaseStamp;
import shangyou.core.model.Favorite;
import shangyou.core.model.StampDetail;
import shangyou.core.model.User;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/user")
@Api(value = "User", tags = "用户")
public class SyUserController {

    @Autowired
    private UserController userController;
    @Autowired
    private CheckCodeController checkCodeController;
    @Autowired
    private FavoriteController favoritecontroller;
    @Autowired
    private BaseStampController baseStampController;

    @ApiOperation(value = "用户登录/注册", notes = "用户登录和注册", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public SApiResponse<LoginOrRegResponseData> userLogin(@RequestBody @Valid SApiRequest<MobileNumberAndCheckCodeRequestData> request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new SApiResponse<>(ErrMsg.RC_MISS_PARAM, bindingResult.getFieldError().getDefaultMessage());
        }
        MobileNumberAndCheckCodeRequestData requestData = request.getData();
        String mobileNumber = requestData.getMobileNumber();
        String checkCode = requestData.getCheckCode();
        User user = userController.userRegOrLogin(mobileNumber, checkCode);
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

    @ApiOperation(value = "用户收藏", notes = "根据邮票id收藏；状态值status：1-收藏状态，2-未收藏状态", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/favorite/add", method = {RequestMethod.POST})
    public SApiResponse<Favorite> userFavorite(@RequestBody @Valid SApiRequest<FavoriteRequestData> request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new SApiResponse<>(ErrMsg.RC_MISS_PARAM, bindingResult.getFieldError().getDefaultMessage());
        }
        FavoriteRequestData favoriteRequestData = request.getData();
        LoginInfo loginInfo = request.getLoginInfo();
        if (!ApiUtility.checkLoginInfo(loginInfo)) {
            return new SApiResponse<>(ErrMsg.RC_NOT_LOGGED_IN);
        }

        String uid = request.getLoginInfo().getUid();
        String stid = favoriteRequestData.getStid();
        int status = favoriteRequestData.getStatus();
        Favorite favorite = favoritecontroller.userFavorite(uid, stid, status);
        if (favorite == null) {
            return new SApiResponse<>(favoritecontroller.getLastErrCode(),favoritecontroller.getLastErrMsg());
        }
        return new SApiResponse<>(ErrMsg.RC_OK, favorite);
    }

    @ApiOperation(value = "展现用户收藏", notes = "根据用户uid展现收藏", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/favorite/show", method = {RequestMethod.POST})
    public SApiResponse<BaseStamp> showFavorite(@RequestBody @Valid SApiRequest request) {
        LoginInfo loginInfo = request.getLoginInfo();
        if (!ApiUtility.checkLoginInfo(loginInfo)) {
            return new SApiResponse<>(ErrMsg.RC_NOT_LOGGED_IN);
        }
        BaseStamp baseStamp = null;
        String uid = request.getLoginInfo().getUid();
        List<Favorite> list = favoritecontroller.queryFavoriteByUid(uid);
        if (list == null) {
            return null;
        }
        for (Favorite f : list) {
            if (f.getStatus() == 1) {
                String stid = f.getStid();
                baseStamp = baseStampController.queryBaseStampByStampId(stid);
            }
        }
        return new SApiResponse<>(ErrMsg.RC_OK, baseStamp);
    }

    @ApiOperation(value = "用户修改信息", notes = "根据登录信息中的uid修改", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public SApiResponse<User> userUpdateGender(@RequestBody @Valid SApiRequest<UserUpdateData> request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new SApiResponse<>(ErrMsg.RC_MISS_PARAM, bindingResult.getFieldError().getDefaultMessage());
        }
        UserUpdateData userUpdateData = request.getData();
        LoginInfo loginInfo = request.getLoginInfo();
        if (!ApiUtility.checkLoginInfo(loginInfo)) {
            return new SApiResponse<>(ErrMsg.RC_NOT_LOGGED_IN);
        }

        String uid = loginInfo.getUid();
        String avatar = userUpdateData.getAvatar();
        String nickname = userUpdateData.getNickname();
        int gender = userUpdateData.getGender();
        Map<String, Object> map = new HashMap<>();
        map.put("avatar", avatar);
        map.put("nickname", nickname);
        map.put("gender", gender);
        User user = userController.userUpdate(uid, map);
        if (ObjectUtils.isEmpty(user)) {
            return new SApiResponse<>(userController.getLastErrCode(), userController.getLastErrMsg());
        }
        return new SApiResponse<>(ErrMsg.RC_OK, user);
    }

}

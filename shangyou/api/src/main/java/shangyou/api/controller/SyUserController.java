package shangyou.api.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import shangyou.api.model.SApiRequest;
import shangyou.api.model.SApiResponse;
import shangyou.api.model.req.UserLoginRequestData;
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
    public SApiResponse<User> userLogin(@RequestBody @Valid SApiRequest<UserLoginRequestData> request){
        UserLoginRequestData requestData = request.getData();
        String phone = requestData.getPhone();
        String checkCode = requestData.getCheckCode();
        userController.userLogin(phone, checkCode);
        return new SApiResponse<>(userController.getLastErrCode(),userController.getLastErrMsg());

    }



}

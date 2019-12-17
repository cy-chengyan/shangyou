package shangyou.core.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import shangyou.core.common.ErrMsg;
import shangyou.core.data.redis.CheckCodeRepo;

import java.util.Random;

@Slf4j
@Component
public class CheckCodeController extends BaseController {

    @Autowired
    private CheckCodeRepo checkCodeRepo;

    private boolean sendCheckCodeSms(String mobileNumber, String checkCode) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "eehtl6b4ptxf9z4jcqxmrfz3", "m2a7yFO2qJCH+gjJffGsv4G8PVI=");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobileNumber);
        request.putQueryParameter("SignName", "赏邮");
        request.putQueryParameter("TemplateCode", "SMS_180342138");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + checkCode + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info(response.getData());
        } catch (Exception e) {
            log.error("sendCheckCodeSms({}, {}) failed", mobileNumber, checkCode, e);
            return false;
        }
        return true;
    }

    public boolean sendCheckCode(String mobileNumber) {
        // TODO: 60秒内不允许再获取验证码

        Random random = new Random();
        String checkCode = random.nextInt() + "0000";
        checkCode = checkCode.substring(0, 4);

        if (!sendCheckCodeSms(mobileNumber, checkCode)) {
            setLastErrWithPredefined(ErrMsg.RC_SERVER_INTERNAL);
            return false;
        }

        return checkCodeRepo.set(mobileNumber, checkCode);
    }

    public boolean isMatched(String mobileNumber, String checkCode) {
        String rightCheckCode = checkCodeRepo.get(mobileNumber);
        if (StringUtils.isEmpty(rightCheckCode) || !rightCheckCode.equals(checkCode)) {
            setLastErrWithPredefined(ErrMsg.RC_CHECK_CODE_ERR);
            return false;
        }
        return true;
    }

}

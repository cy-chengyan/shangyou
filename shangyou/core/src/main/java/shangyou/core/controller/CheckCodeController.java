package shangyou.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import shangyou.core.data.redis.CheckCodeRepo;


@Component
public class CheckCodeController extends BaseController {

    @Autowired
    private CheckCodeRepo checkCodeRepo;

    public static String randomCheckCode() {

        String[] strs = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String code = "";
        for (int j=0; j<4; j++){
            int i = (int)(Math.random()*10);
            code = code + strs[i];
        }
        System.out.println(code);
        return code;
    }

    private boolean sendCheckCode(String mobileNumber) {
        String checkCode = randomCheckCode(); // TODO: 长度为4的数字验证码


        // TODO: 调用第三方平台的API，把短信发出去

        return checkCodeRepo.set(mobileNumber, checkCode);
    }

    private boolean isMatched(String mobileNumber, String checkCode) {
        String rightCheckCode = checkCodeRepo.get(mobileNumber);
        if (StringUtils.isEmpty(rightCheckCode)) {
            return false;
        }
        return rightCheckCode.equals(checkCode);
    }

}



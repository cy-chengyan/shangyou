package shangyou.api.misc;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.util.StringUtils;
import shangyou.api.model.LoginInfo;
import shangyou.core.model.User;

import java.time.ZonedDateTime;

public class ApiUtility {

    private static final String LOGIN_SALT = "_3.14_^$@&^@$!";

    public static LoginInfo generalLoginInfoWithUser(User user) {
        long now = ZonedDateTime.now().toEpochSecond();
        String src = now + user.getUid() + LOGIN_SALT;
        String token = Md5Crypt.md5Crypt(src.getBytes()).substring(0, 16);
        return LoginInfo.builder()
                .uid(user.getUid())
                .token(token)
                .loginAt(now)
                .build();
    }

    public static boolean checkLoginInfo(LoginInfo loginInfo) {
        if (loginInfo == null) {
            return false;
        }

        String uid = loginInfo.getUid();
        String token = loginInfo.getToken();
        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(token) || token.length() != 16) {
            return false;
        }

        long loginAt = loginInfo.getLoginAt();
        String src = loginAt + uid + LOGIN_SALT;
        String rightToken = Md5Crypt.md5Crypt(src.getBytes()).substring(16);
        return rightToken.equals(token);
    }

}

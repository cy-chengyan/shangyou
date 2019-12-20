package shangyou.core.common;

public class ErrMsg {

    public static final int RC_OK                      = 0;
    public static final int RC_NOT_FOUND               = -1;
    public static final int RC_MISS_PARAM              = -2;
    public static final int RC_PARAM_ERR               = -3;
    public static final int RC_FUNC_NOT_SUPPORTED      = -4;
    public static final int RC_FUNC_CALL_FAILED        = -5;
    public static final int RC_SERVER_INTERNAL         = -6;
    public static final int RC_CHECK_CODE_ERR          = -7;
    public static final int RC_NOT_FOUND_USER          = -8;
    public static final int RC_USER_ALREADY_EXISTS     = -9;
    public static final int RC_OPERATING_FREQUENCY     = -10;
    public static final int RC_NICKNAME_USED           = -11;
    public static final int RC_NOT_LOGGED_IN           = -12;
    public static final int RC_INITIAL_STATE_ERR       = -13;
    public static final int RC_MOBILE_NUMBER_ALREADY_EXISTS = -14;

    public static final String toString(int code) {
        final String[] errMsgs = {
                "OK",
                "找不到相关的记录",
                "缺少必须参数",
                "参数不正确",
                "不支持该功能",
                "功能请求失败",
                "服务器内部错误",
                "验证码错误",
                "该用户不存在",
                "用户已存在",
                "操作频繁",
                "昵称已被使用",
                "未登录",
                "初始状态错误",
                "该号码已存在"
        };

        String errMsg;
        int c = Math.abs(code);
        if (c < 0 || c >= errMsgs.length)
            errMsg = "未知错误";
        else
            errMsg = errMsgs[c];

        return errMsg;
    }

}

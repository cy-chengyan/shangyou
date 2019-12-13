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

    public static final String toString(int code) {
        final String[] errMsgs = {
                "OK",
                "找不到相关的记录",
                "缺少必须参数",
                "参数不正确",
                "不支持该功能",
                "功能请求失败",
                "服务器内部错误",
                "验证码错误"
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
package shangyou.core.controller;

import shangyou.core.common.ErrMsg;

public abstract class BaseController {

    protected int lastErrCode = ErrMsg.RC_OK;
    protected String lastErrMsg;

    public void resetLastErr() {
        lastErrCode = ErrMsg.RC_OK;
        lastErrMsg = null;
    }

    public int getLastErrCode() {
        return lastErrCode;
    }

    public String getLastErrMsg() {
        return lastErrMsg;
    }

    public void setLastErrWithPredefined(int predefinedErr) {
        lastErrCode = predefinedErr;
        lastErrMsg = ErrMsg.toString(predefinedErr);
    }

}

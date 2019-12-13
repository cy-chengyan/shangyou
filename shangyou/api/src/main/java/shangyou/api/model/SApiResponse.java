package shangyou.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import shangyou.core.common.ErrMsg;
import shangyou.core.common.Utility;

@Builder
@Setter
@Getter
@ApiModel("返回数据")
public class SApiResponse<T> {

    @ApiModelProperty("错误码")
    private int code;

    @ApiModelProperty("错误描述")
    private String msg;

    @ApiModelProperty("数据对象")
    private T data;

    public SApiResponse() {
        this(ErrMsg.RC_OK);
    }

    public SApiResponse(int code) {
        this.code = code;
        this.msg = ErrMsg.toString(this.code);
    }

    public SApiResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public SApiResponse(int code, T data) {
        this(code);
        this.data = data;
    }

    public SApiResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return Utility.objToJsonString(this);
    }

}

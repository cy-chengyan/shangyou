package shangyou.api.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestData {

    @ApiModelProperty("手机号码")
    @NonNull
    private String phone;

    @ApiModelProperty("验证码")
    @NonNull
    private String checkCode;
}

package shangyou.api.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestData {

    @ApiModelProperty("手机号码")
    @NotBlank
    @JsonProperty("mobile_number")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    private String mobileNumber;

    @ApiModelProperty("验证码")
    @NotBlank
    @JsonProperty("check_code")
    private String checkCode;
}

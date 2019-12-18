package shangyou.api.model.req;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationCheckCodeData {

    @ApiModelProperty("手机号")
    @JsonProperty("mobile_number")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    @NotBlank
    private String mobileNumber;

    @ApiModelProperty("验证码")
    @JsonProperty("check_code")
    @NotBlank
    private String checkCode;
}

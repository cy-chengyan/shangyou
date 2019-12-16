package shangyou.api.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestData {

    @ApiModelProperty("手机号码")
    @NotBlank
    private String phone;

    @ApiModelProperty("验证码")
    @NotBlank
    @JsonProperty("check_code")
    private String checkCode;
}

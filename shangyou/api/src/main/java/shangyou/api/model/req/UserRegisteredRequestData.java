package shangyou.api.model.req;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisteredRequestData {


    @ApiModelProperty("手机号码")
    @NotBlank
    private String phone;

    @ApiModelProperty("验证码")
    @NotBlank
    @JsonProperty("check_code")
    private String checkCode;

    @ApiModelProperty("昵称")
    @NotBlank
    private String nickname;

    @ApiModelProperty("性别")
    @Min(1)
    @Max(3)
    private int gender = 3;

}

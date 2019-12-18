package shangyou.api.model.req;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shangyou.core.common.Constant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendCheckCodeData {

    @ApiModelProperty("手机号")
    @JsonProperty("mobile_number")
    @NotBlank
    @Pattern(regexp = Constant.MOBILE_NUMBER_REGEX, message = "手机号码格式错误")
    private String mobileNumber;

}

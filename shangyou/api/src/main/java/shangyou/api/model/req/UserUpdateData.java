package shangyou.api.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shangyou.core.common.Constant;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateData {
    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("用户性别")
    @Min(value = 1, message = "性别最小值是1")
    @Max(value = 3, message = "性别最大值是3")
    private int gender;

    @ApiModelProperty("用户电话号码")
    @JsonProperty("mobile_number")
    @Pattern(regexp = Constant.MOBILE_NUMBER_REGEX, message = "手机号码格式错误")
    private String mobileNumber;
}

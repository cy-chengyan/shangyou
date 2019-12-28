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
    private int gender;

    @ApiModelProperty("用户昵称")
    @JsonProperty("nickname")
    private String nickname;

}

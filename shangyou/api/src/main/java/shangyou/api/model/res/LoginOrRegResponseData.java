package shangyou.api.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import shangyou.api.model.LoginInfo;
import shangyou.core.model.User;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginOrRegResponseData {

    @ApiModelProperty("用户信息")
    private User user;

    @ApiModelProperty("登录信息")
    @JsonProperty("login_info")
    private LoginInfo loginInfo;

}

package shangyou.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {

    @ApiModelProperty("用户id")
    private String uid;

    @ApiModelProperty("token信息")
    private String token;

    @ApiModelProperty("登录时间戳")
    @JsonProperty("login_at")
    private long loginAt;

}

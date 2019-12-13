package shangyou.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;



@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("收藏夹")
public class Favorite {
    @ApiModelProperty("收藏夹id")
    private String faid;

    @ApiModelProperty("用户id")
    private String uid;

    @ApiModelProperty("邮票id")
    private String stid;

    @ApiModelProperty("创建时间")
    @JsonProperty("created_at")
    private int createdAt;
}

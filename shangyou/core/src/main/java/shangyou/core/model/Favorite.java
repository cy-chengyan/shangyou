package shangyou.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/*
      `faid` char(8) PRIMARY KEY,
       `uid` char(8) NOT NULL COMMENT '用户id',
       `stid` char(8) NOT NULL COMMENT '邮票id',
       `created_at` bigint(20) NOT NULL COMMENT '创建时间',
 */

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

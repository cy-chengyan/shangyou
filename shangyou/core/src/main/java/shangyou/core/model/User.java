package shangyou.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/*
        `uid` char(8) PRIMARY KEY COMMENT '用户id',
       `mobile_number` varchar(32) NOT NULL COMMENT '手机号码',
       `nickname` varchar(32) NOT NULL COMMENT '昵称',
       `gender` tinyint(1) NOT NULL COMMENT '性别,1男,2女,3未知',
       `created_at` bigint(20) NOT NULL COMMENT '创建时间',
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户")
public class User {
    @ApiModelProperty("用户id")
    private String uid;

    @ApiModelProperty("手机号码")
    @JsonProperty("mobile_number")
    private String mobileNumber;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("性别,1男,2女,3未知")
    private int gender;

    @ApiModelProperty("创建时间")
    @JsonProperty("created_at")
    private int createdAt;
}

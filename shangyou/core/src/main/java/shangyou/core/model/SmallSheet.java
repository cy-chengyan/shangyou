package shangyou.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/*
        `slsid` char(8) PRIMARY KEY COMMENT '小型张id',
       `stid` char(8) COMMENT '邮票id',
       `face_value` varchar(64) COMMENT '小型张面值',
       `size` varchar(64) COMMENT '小型张尺寸',
       `image` varchar(256) COMMENT '小型张邮票主图',
       `chikong` varchar(128) COMMENT '小型张齿孔',
       `designer`  varchar(64) COMMENT '邮票设计',
       `draw` varchar(32) COMMENT '小型张绘画'
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("小型张")
public class SmallSheet {
    @ApiModelProperty("小型张id")
    private String slsid;

    @ApiModelProperty("邮票id")
    private String stid;

    @ApiModelProperty("小型张面值")
    @JsonProperty("face_value")
    private String faceValue;

    @ApiModelProperty("小型张尺寸")
    private String size;

    @ApiModelProperty("小型张邮票主图")
    private String image;

    @ApiModelProperty("小型张齿孔")
    private String chikong;

    @ApiModelProperty("邮票设计")
    private String designer;

    @ApiModelProperty("小型张绘画")
    private String draw;
}

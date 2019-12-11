package shangyou.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/*
        `minid` char(8) PRIMARY KEY COMMENT '小全张id',
       `stid` char(8) COMMENT '邮票id',
       `face_value` varchar(8) COMMENT '面值',
       `size` varchar(64) COMMENT '尺寸'
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("小全张")
public class MiniSheet {
    @ApiModelProperty("小全张id")
    private String minid;

    @ApiModelProperty("邮票id")
    private String stid;

    @ApiModelProperty("面值")
    @JsonProperty("face_value")
    private String faceValue;

    @ApiModelProperty("尺寸")
    private String size;
}

package shangyou.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/*
         `fsid` char(8) PRIMARY KEY COMMENT '四连体小型张id',
       `stid` char(8) COMMENT '邮票id',
       `face_value` varchar(8) COMMENT '面值',
       `size` varchar(64) COMMENT '尺寸'
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("四连体小型张")
public class FourSheet {
    @ApiModelProperty("四连体小型张id")
    private String fsid;

    @ApiModelProperty("邮票id")
    private String stid;

    @ApiModelProperty("面值")
    @JsonProperty("face_value")
    private String faceValue;

    @ApiModelProperty("尺寸")
    private String size;
}

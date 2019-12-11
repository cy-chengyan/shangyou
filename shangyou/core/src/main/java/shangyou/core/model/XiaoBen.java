package shangyou.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/*
        `xiaobenid` char(8) PRIMARY KEY COMMENT '小本票id',
       `stid` char(8) COMMENT '邮票id',
       `number` varchar(32) COMMENT '小本票编号',
       `size` varchar(64) COMMENT '尺寸',
       `issued_number` varchar(64) COMMENT '发行量',
       `face_value` varchar(8) COMMENT '小本票面值',
       `designer` varchar(64) COMMENT '邮票设计',
       `editor` varchar(64) COMMENT '责任编辑',
       `printing_house` varchar(32) COMMENT '印刷厂'
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("小本票")
public class XiaoBen {
    @ApiModelProperty("小本票id")
    private String xiaobenid;

    @ApiModelProperty("邮票id")
    private String stid;

    @ApiModelProperty("小本票编号")
    private String number;

    @ApiModelProperty("尺寸")
    private String size;

    @ApiModelProperty("发行量")
    @JsonProperty("issued_number")
    private String issuedNumber;

    @ApiModelProperty("小本票面值")
    @JsonProperty("face_value")
    private String faceValue;

    @ApiModelProperty("邮票设计")
    private String designer;

    @ApiModelProperty("责任编辑")
    private String editor;

    @ApiModelProperty("印刷厂")
    @JsonProperty("printing_house")
    private String printingHouse;
}

package shangyou.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/*
        `slfid` char(8) PRIMARY KEY COMMENT '小版id',
       `stid` char(8) COMMENT '邮票id',
       `slsize` varchar(64) COMMENT '小版尺寸',
       `slnumber` varchar(64) COMMENT '小版枚数',
       `issued_number` varchar(64) COMMENT '发行量'
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("小版")
public class SmallFormat {
    @ApiModelProperty("小版id")
    private String slfid;

    @ApiModelProperty("邮票id")
    private String stid;

    @ApiModelProperty("小版尺寸")
    private String slsize;

    @ApiModelProperty("小版枚数")
    private String slnumber;

    @ApiModelProperty("发行量")
    @JsonProperty("issued_number")
    private String issuedNumber;
}

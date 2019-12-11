package shangyou.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/*
`bgid` char(8) PRIMARY KEY COMMENT '大版id',
       `stid` char(8) COMMENT '邮票id',
       `bgsize` varchar(64) COMMENT '大版尺寸',
       `bgnumber` varchar(128) COMMENT '大版枚数'
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("大版")
public class BigFormat {
    @ApiModelProperty("大版id")
    private String bgid;

    @ApiModelProperty("邮票id")
    private  String stid;

    @ApiModelProperty("大版尺寸")
    private String bgsize;

    @ApiModelProperty("大版枚数")
    private String bgnumber;
}

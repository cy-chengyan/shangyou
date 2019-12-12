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
@ApiModel("子邮票")
public class SubStamp {

    @ApiModelProperty("子邮票id")
    private String sstid;

    @ApiModelProperty("邮票id")
    private String stid;

    @ApiModelProperty("序号")
    private int order;

    @ApiModelProperty("邮票标题")
    private String title;

    @ApiModelProperty("邮票图片")
    private String picture;

    @ApiModelProperty("邮票面值")
    @JsonProperty("face_value")
    private String faceValue;

    @ApiModelProperty("发行量")
    @JsonProperty("issued_number")
    private String issuedNumber;

}

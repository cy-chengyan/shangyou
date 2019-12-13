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

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

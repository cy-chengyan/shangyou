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

package shangyou.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;



@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("双联小型张")
public class DoubleSheet {
    @ApiModelProperty("双联小型张id")
    private String dsid;

    @ApiModelProperty("邮票id")
    private String stid;

    @ApiModelProperty("尺寸")
    private String size;
}

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
@ApiModel("赠送版")
public class ZengSong {
    @ApiModelProperty("赠送版id")
    private String zengid;

    @ApiModelProperty("邮票id")
    private String stid;

    @ApiModelProperty("赠送版尺寸")
    @JsonProperty("zeng_size")
    private String zengSize;

    @ApiModelProperty("赠送版枚数")
    @JsonProperty("zeng_number")
    private String zengNumber;
}

package shangyou.api.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimilarStampRequestData {

    @ApiModelProperty("邮票id")
    @NotBlank
    private String stid;

    @ApiModelProperty("数量")
    @Min(1)
    @Max(10)
    private int size;

}

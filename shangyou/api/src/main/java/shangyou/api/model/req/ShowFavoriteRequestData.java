package shangyou.api.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowFavoriteRequestData {
    @ApiModelProperty("记录偏移量")
    @Min(value = 0, message = "偏移量不能小于0")
    private int offset;

    @ApiModelProperty("请求记录数")
    @Min(1)
    @Max(10)
    private int size;
}

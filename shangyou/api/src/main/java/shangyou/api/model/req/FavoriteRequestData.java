package shangyou.api.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteRequestData {
    @ApiModelProperty("邮票id")
    private String stid;

    @ApiModelProperty("邮票收藏状态值,1-收藏,2-未收藏")
    @NotNull
    @Min(value = 1, message = "收藏状态值最小是1")
    @Max(value = 2, message = "收藏状态值最大是2")
    private int status;
}

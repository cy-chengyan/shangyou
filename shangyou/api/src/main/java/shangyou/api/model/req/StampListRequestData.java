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
public class StampListRequestData {

    @ApiModelProperty("邮票类型")
    @NotBlank(message = "邮票类型不能为空")
    private String type;

    @ApiModelProperty("发布年份")
    private String year;

    @ApiModelProperty("记录偏移量")
    @Min(value = 0, message = "记录偏移量不能小于0")
    private int offset;

    @ApiModelProperty("请求记录数")
    @Min(1)
    @Max(20)
    private int size;

}

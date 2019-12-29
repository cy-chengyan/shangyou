package shangyou.api.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StampSearchRequestData {

    @ApiModelProperty("查询关键词")
    private String query;

    @ApiModelProperty("中国邮票类型, 0所有,1编年邮票,2`纪`字头邮票,3`特`字头邮票,4`J`字头邮票,5`T`字头邮票,6普通邮票,7编号邮票,8文革邮票,9军用邮票,10个性化服务专用邮票,11贺年专用邮票,12贺卡专用邮票,13航空邮票,14欠资邮票,15加字改值邮票,16包裹邮票")
    @Min(0)
    @Max(16)
    private int type;

    @ApiModelProperty("发布年份, 0所有")
    @Min(0)
    private int year;

    @ApiModelProperty("记录偏移量")
    @Min(value = 0, message = "记录偏移量不能小于0")
    private int offset;

    @ApiModelProperty("请求记录数")
    @Min(1)
    @Max(20)
    private int size;

}

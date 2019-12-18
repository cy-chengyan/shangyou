package shangyou.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import shangyou.core.common.ErrMsg;
import shangyou.core.common.Utility;

import javax.validation.Valid;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("输入数据")
public class SApiRequest<T> {

    @ApiModelProperty("数据对象")
    @Valid
    T data;

}

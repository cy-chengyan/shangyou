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
public class UserUpdateGenderData {
    @ApiModelProperty("用户性别")
    @NotNull
    @Min(value = 1, message = "性别最小值是1")
    @Max(value = 3, message = "性别最大值是3")
    private int gender;
}

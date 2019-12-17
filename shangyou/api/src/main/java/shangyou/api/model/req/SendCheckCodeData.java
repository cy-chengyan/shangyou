package shangyou.api.model.req;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendCheckCodeData {

    @ApiModelProperty("手机号")
    @JsonProperty("mobile_phone")
    @NotBlank
    private String mobilePhone;
}

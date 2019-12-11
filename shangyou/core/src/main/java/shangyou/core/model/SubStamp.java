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
@ApiModel("子邮票")
public class SubStamp {

    @ApiModelProperty("子邮票id")
    private String sstid;

    private String stid;

    private int order;

    private String title;

    private String picture;

    @JsonProperty("face_value")
    private String faceValue;

    @JsonProperty("issued_number")
    private String issuedNumber;

}

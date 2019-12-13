package shangyou.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("邮票细节")
public class StampDetail {

    @ApiModelProperty("邮票信息")
    @JsonProperty("base_stamp")
    private BaseStamp baseStamp;

    @ApiModelProperty("子邮票")
    @JsonProperty("sub_stamps")
    private List<SubStamp> subStamps;

    @ApiModelProperty("大版")
    @JsonProperty("big_formats")
    private List<BigFormat> bigFormats;

    @ApiModelProperty("小版")
    @JsonProperty("small_formats")
    private List<SmallFormat> smallFormats;

    @ApiModelProperty("赠送版")
    @JsonProperty("zeng_songs")
    private List<ZengSong>  zengSongs;

    @ApiModelProperty("小本票")
    @JsonProperty("xiao_bens")
    private List<XiaoBen> xiaoBens;

    @ApiModelProperty("小型张")
    @JsonProperty("small_sheets")
    private List<SmallSheet> smallSheets;

    @ApiModelProperty("小全张")
    @JsonProperty("mini_sheets")
    private List<MiniSheet> miniSheets;

    @ApiModelProperty("四连体小型张")
    @JsonProperty("four_sheets")
    private List<FourSheet> fourSheets;

    @ApiModelProperty("双联小型张")
    @JsonProperty("double_sheets")
    private List<DoubleSheet> doubleSheets;

}

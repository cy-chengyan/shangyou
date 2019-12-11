package shangyou.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("邮票细节")
public class StampDetail {

    @ApiModelProperty("邮票信息")
    private BaseStamp baseStamp;

    @ApiModelProperty("子邮票")
    private List<SubStamp> subStamps;

    @ApiModelProperty("大版")
    private List<BigFormat> bigFormats;

    @ApiModelProperty("小版")
    private List<SmallFormat> smallFormats;

    @ApiModelProperty("赠送版")
    private List<ZengSong>  zengSongs;

    @ApiModelProperty("小本票")
    private List<XiaoBen> xiaoBens;

    @ApiModelProperty("小型张")
    private List<SmallSheet> smallSheets;

    @ApiModelProperty("小全张")
    private List<MiniSheet> miniSheets;

    @ApiModelProperty("四连体小型张")
    private List<FourSheet> fourSheets;

    @ApiModelProperty("双联小型张")
    private List<DoubleSheet> doubleSheets;

}

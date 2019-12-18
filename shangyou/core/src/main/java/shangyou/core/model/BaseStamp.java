package shangyou.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("邮票基本信息")
public class BaseStamp {
    @ApiModelProperty("邮票id")
    private String stid;

    @ApiModelProperty("类型")
    private int type;

    @ApiModelProperty("发布年份")
    private int year;

    @ApiModelProperty("邮票标题")
    private String name;

    @ApiModelProperty("国家标示, 1-中国")
    private int countryid;

    @ApiModelProperty("邮票编号")
    private String number;

    @ApiModelProperty("邮票发布日期")
    @JsonProperty("issued_date")
    private String issuedDate;

    @ApiModelProperty("联合发行")
    @JsonProperty("joint_issue")
    private String jointIssue;

    @ApiModelProperty("邮票尺寸")
    private String size;

    @ApiModelProperty("邮票齿孔")
    private String chikong;

    @ApiModelProperty("邮票版式")
    private String format;

    @ApiModelProperty("防伪技术")
    private String fanwei;

    @ApiModelProperty("邮票设计")
    private String designer;

    @ApiModelProperty("责任编辑")
    private String editor;

    @ApiModelProperty("雕刻者")
    private String carve;

    @ApiModelProperty("边饰设计")
    @JsonProperty("side_design")
    private String sideDesign;

    @ApiModelProperty("绘画")
    private String draw;

    @ApiModelProperty("摄影")
    private String shoot;

    @ApiModelProperty("印刷厂")
    @JsonProperty("printing_house")
    private String printingHouse;

    @ApiModelProperty("背景资料")
    private String background;

    @ApiModelProperty("邮票图片")
    private List<String> pictures;

}

package shangyou.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/*
        `stid` char(8) PRIMARY KEY COMMENT '邮票id',
       `countryid` tinyint COMMENT '国家, 1-中国,',
       `number` varchar(32) NOT NULL COMMENT '邮票编号',
       `issued_date` char(32) COMMENT '邮票发布日期',
       `joint_issue` varchar(64) COMMENT '联合发行',
       `size` varchar(128) COMMENT '邮票尺寸',
       `chikong` varchar(128) COMMENT '邮票齿孔',
       `format` varchar(64) COMMENT '邮票版式',
       `fanwei` varchar(64) COMMENT '防伪技术',
       `designer` varchar(64) COMMENT '邮票设计',
       `editor` varchar(64) COMMENT '责任编辑',
       `carve` varchar(64) COMMENT '雕刻者',
       `side_design` varchar(64) COMMENT '边饰设计',
       `draw` varchar(64) COMMENT '绘画',
       `shoot` varchar(64) COMMENT '摄影',
       `printing_house` varchar(128) COMMENT '印刷厂',
       `background` text COMMENT '背景资料'
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("邮票基本信息")
public class BaseStamp {
    @ApiModelProperty("邮票id")
    private String stid;

    @ApiModelProperty("国家标示")
    private  int countryid;

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
    private String  format;

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

}

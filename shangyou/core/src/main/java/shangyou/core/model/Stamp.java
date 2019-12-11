package shangyou.core.model;

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
public class Stamp {
    private String stid;
    private  int countruid;
    private String number;
    private String issuedDate;
    private String jointIssue;
    private String size;
    private String chikong;
    private String  format;
    private String fanwei;
    private String designer;
    private String editor;
    private String carve;
    private String sideDesign;
    private String draw;
    private String shoot;
    private String printingHouse;
    private String background;

}

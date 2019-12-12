package shangyou.core.data.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.BaseStamp;

import java.util.List;


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
@Repository
@Mapper
public interface BaseStampMapper {
    @Select({
            "<script>",
            "SELECT stid, countryid, `number`, issued_date, joint_issue, size, chikong, format, fanwei, designer, editor," +
                    "carve, side_design, draw, shoot, printing_house, background FROM t_stamp WHERE stid = #{stid} limit 1",
            "</script>"
    })
    @Results({
            @Result(property = "issuedDate", column = "issued_date"),
            @Result(property = "jointIssue", column = "joint_issue"),
            @Result(property = "sideDesign", column = "side_design"),
            @Result(property = "printingHouse", column = "printing_house")
    })
    BaseStamp queryStampByStid(@Param("stid") String stid);

    @Select({
            "<script>",
            "SELECT stid, countryid, `number`, issued_date, joint_issue, size, chikong, format, fanwei, designer, editor,",
            " carve, side_design, draw, shoot, printing_house, background FROM t_stamp WHERE 1 = 1",
            "<if test=\"type != null\"> AND `number` LIKE '#{type}%'</if>",
            "<if test=\"year != null\"> AND LEFT(issued_date, 4) == #{type}</if>",
            "<if test=\"offset >= 0 and size > 0\"> LIMIT ${offset}, #{size}</if>",
            "</script>"
    })
    @Results({
            @Result(property = "issuedDate", column = "issued_date"),
            @Result(property = "jointIssue", column = "joint_issue"),
            @Result(property = "sideDesign", column = "side_design"),
            @Result(property = "printingHouse", column = "printing_house")
    })
    List<BaseStamp> queryStamp(@Param("type")String type, @Param("year")String year, @Param("offset")int offset, @Param("size")int size);

}

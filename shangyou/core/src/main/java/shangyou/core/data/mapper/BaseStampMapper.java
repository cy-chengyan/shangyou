package shangyou.core.data.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.BaseStamp;

import java.util.List;



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

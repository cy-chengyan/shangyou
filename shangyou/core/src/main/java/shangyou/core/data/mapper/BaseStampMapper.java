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
            "SELECT stid, `type`, year, `name`, countryid, `number`, issued_date, joint_issue, size, chikong, format, fanwei, designer, editor," +
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
            "SELECT stid, `type`, year, `name`, countryid, `number`, issued_date, joint_issue, size, chikong, format, fanwei, designer, editor,",
            " carve, side_design, draw, shoot, printing_house, background FROM t_stamp WHERE 1 = 1",
            "<if test=\"type != 0\"> AND `type` = #{type}</if>",
            "<if test=\"year != 0\"> AND year = #{year}</if>",
            " ORDER BY issued_date DESC",
            "<if test=\"offset >= 0 and size > 0\"> LIMIT ${offset}, #{size}</if>",
            "</script>"
    })
    @Results({
            @Result(property = "issuedDate", column = "issued_date"),
            @Result(property = "jointIssue", column = "joint_issue"),
            @Result(property = "sideDesign", column = "side_design"),
            @Result(property = "printingHouse", column = "printing_house")
    })
    List<BaseStamp> queryStamp(@Param("type")int type, @Param("year")int year, @Param("offset")int offset, @Param("size")int size);

}

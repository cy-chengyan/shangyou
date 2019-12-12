package shangyou.core.data.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.XiaoBen;

import java.util.List;


@Repository
@Mapper
public interface XiaoBenMapper {
    @Select({
            "<script>",
            "SELECT xiaobenid, stid, `number`, size, issued_number, face_value, designer, editor, printing_house",
            "FROM t_xiaoben WHERE stid = #{stid}",
            "</script>"
    })
    @Results({
            @Result(property = "issuedNumber", column = "issued_number"),
            @Result(property = "faceValue", column = "face_value"),
            @Result(property = "printingHouse", column = "printing_house")
    })
    List<XiaoBen> queryStampByStid(@Param("stid")String stid);
}

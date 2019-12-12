package shangyou.core.data.mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.SmallSheet;

import java.util.List;


@Repository
@Mapper
public interface SmallSheetMaaper {
    @Select({
            "<script>",
            "SELECT slsid, stid, face_value, size, image, chikong, designer, draw FROM t_small_sheet WHERE stid = #{stid}",
            "</script>"
    })
    @Results({
            @Result(property = "faceValue", column = "face_value")
    })
    List<SmallSheet> queryStampByStid(@Param("stid") String stid);
}

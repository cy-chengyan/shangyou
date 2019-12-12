package shangyou.core.data.mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.MiniSheet;

import java.util.List;


@Repository
@Mapper
public interface MiniSheetMapper {
    @Select({
            "<script>",
            "SELECT minid, stid, face_value, size FROM t_mini_sheet WHERE stid = #{stid}",
            "</script>"
    })
    @Results({
            @Result(property = "faceValue", column = "face_value")
    })
    List<MiniSheet> queryStampByStid(@Param("stid")String stid);
}

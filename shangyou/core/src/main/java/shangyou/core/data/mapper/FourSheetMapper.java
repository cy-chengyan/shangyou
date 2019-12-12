package shangyou.core.data.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.FourSheet;

import java.util.List;


@Repository
@Mapper
public interface FourSheetMapper {
    @Select({
            "<script>",
            "SELECT fsid, stid, face_value, size FROM t_four_sheet WHERE stid = #{stid}",
            "</script>"
    })
    @Results({
            @Result(property = "faceValue", column = "face_value")
    })
    List<FourSheet> queryStampByStid(@Param("stid")String stid);
}

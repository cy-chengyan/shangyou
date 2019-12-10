package shangyou.core.data.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.SubStamp;

import java.util.List;

@Repository
@Mapper
public interface SubStampMapper {

    @Select({"<script>",
            "SELECT sstid, stid, `order`, title, picture, face_value, issued_number FROM t_sub_stamp WHERE stid = #{stid}",
            "</script>"})
    @Results({
            @Result(property = "faceValue", column = "face_value"),
            @Result(property = "issuedNumber", column = "issued_number")
    })
    List<SubStamp> queryByStampid(@Param("stid") String stid);

}

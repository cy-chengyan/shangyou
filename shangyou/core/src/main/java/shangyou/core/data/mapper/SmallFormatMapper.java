package shangyou.core.data.mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.SmallFormat;

import java.util.List;


@Repository
@Mapper
public interface SmallFormatMapper {
    @Select({
            "<script>",
            "SELECT slfid, stid, slsize, slnumber, issued_number FROM t_small_format WHERE stid = #{stid}",
            "</script>"
    })
    @Results({
            @Result(property = "issuedNumber", column = "issued_number")
    })
    List<SmallFormat> queryStampByStid(@Param("stid")String stid);
}

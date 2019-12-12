package shangyou.core.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import shangyou.core.model.DoubleSheet;

import java.util.List;


@Repository
@Mapper
public interface DoubleSheetMapper {
    @Select({
            "<script>",
            "SELECT dsid, stid, size FROM t_double_sheet WHERE stid = #{stid}",
            "</script>"
    })
    List<DoubleSheet> queryStampByStid(@Param("stid") String stid);
}

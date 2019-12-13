package shangyou.core.data.mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.ZengSong;

import java.util.List;


@Repository
@Mapper
public interface ZengSongMapper {
    @Select({
            "<script>",
            "SELECT zengid, stid, zeng_size, zeng_number FROM t_zengsong WHERE stid = #{stid}",
            "</script>"
    })
    @Results({
            @Result(property = "zengSize", column = "zeng_size"),
            @Result(property = "zengNumber", column = "zeng_number")
    })
    List<ZengSong> queryStampByStid(@Param("stid")String stid);
}

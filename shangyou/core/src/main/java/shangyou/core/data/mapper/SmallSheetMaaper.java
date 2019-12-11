package shangyou.core.data.mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.SmallSheet;

import java.util.List;

/*
        `slsid` char(8) PRIMARY KEY COMMENT '小型张id',
       `stid` char(8) COMMENT '邮票id',
       `face_value` varchar(64) COMMENT '小型张面值',
       `size` varchar(64) COMMENT '小型张尺寸',
       `image` varchar(256) COMMENT '小型张邮票主图',
       `chikong` varchar(128) COMMENT '小型张齿孔',
       `designer`  varchar(64) COMMENT '邮票设计',
       `draw` varchar(32) COMMENT '小型张绘画'
 */
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

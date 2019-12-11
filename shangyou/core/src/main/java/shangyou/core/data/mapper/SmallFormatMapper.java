package shangyou.core.data.mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.SmallFormat;

import java.util.List;

/*
       `slfid` char(8) PRIMARY KEY COMMENT '小版id',
       `stid` char(8) COMMENT '邮票id',
       `slsize` varchar(64) COMMENT '小版尺寸',
       `slnumber` varchar(64) COMMENT '小版枚数',
       `issued_number` varchar(64) COMMENT '发行量'

 */
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

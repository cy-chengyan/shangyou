package shangyou.core.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import shangyou.core.model.BigFormat;

import java.util.List;

/*
        `bgid` char(8) PRIMARY KEY COMMENT '大版id',
       `stid` char(8) COMMENT '邮票id',
       `bgsize` varchar(64) COMMENT '大版尺寸',
       `bgnumber` varchar(128) COMMENT '大版枚数'
 */
@Repository
@Mapper
public interface BigFormatMapper {
    @Select({
            "<script",
            "SELECT bgid, stid, bgsize, bgnumber FROM t_big_format WHERE stid = #{stid}",
            "</script>"
    })
    List<BigFormat> queryStampByStid(@Param("stid") String stid);

}

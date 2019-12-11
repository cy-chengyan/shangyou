package shangyou.core.data.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import shangyou.core.model.XiaoBen;

import java.util.List;

/*
        `xiaobenid` char(8) PRIMARY KEY COMMENT '小本票id',
       `stid` char(8) COMMENT '邮票id',
       `number` varchar(32) COMMENT '小本票编号',
       `size` varchar(64) COMMENT '尺寸',
       `issued_number` varchar(64) COMMENT '发行量',
       `face_value` varchar(8) COMMENT '小本票面值',
       `designer` varchar(64) COMMENT '邮票设计',
       `editor` varchar(64) COMMENT '责任编辑',
       `printing_house` varchar(32) COMMENT '印刷厂'
 */
@Repository
@Mapper
public interface XiaoBenMapper {
    @Select({
            "<script>",
            "SELECT xiaobenid, stid, `number`, size, issued_number, face_value, designer, editor, printing_house FROM t_xiaoben WHERE stid = #{stid}",
            "</script>"
    })
    @Results({
            @Result(property = "issuedNumber", column = "issued_number"),
            @Result(property = "faceValue", column = "face_value"),
            @Result(property = "printingHouse", column = "printing_house")
    })
    List<XiaoBen> queryStampByStid(@Param("stid")String stid);
}

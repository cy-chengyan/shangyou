package shangyou.core.data.repo;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import shangyou.core.common.Constant;
import shangyou.core.data.mapper.BaseStampMapper;
import shangyou.core.model.BaseStamp;

import java.util.List;


@Repository
public class BaseStampRepo {

    @Autowired
    private BaseStampMapper baseStampMapper;

    public void fillFieldPictures(BaseStamp baseStamp) {
        if (baseStamp == null || StringUtils.isEmpty(baseStamp.getPicture())) {
            return;
        }
        List<String> pictures = Lists.newArrayList(baseStamp.getPicture().split(","));
        List<String> fullPathPictures = Lists.newArrayList();
        pictures.forEach(p -> fullPathPictures.add(Constant.IMG_URL_PATH_PREFIX + p));
        baseStamp.setPictures(fullPathPictures);
    }

    public BaseStamp queryStampByStampId(String stid) {
        BaseStamp stamp = baseStampMapper.queryStampByStid(stid);
        fillFieldPictures(stamp);
        return stamp;
    }

    public List<BaseStamp> queryStamp(int type, int year, int offset, int size) {
        List<BaseStamp> baseStamps = baseStampMapper.queryStamp(type, year, offset, size);
        baseStamps.forEach(baseStamp -> fillFieldPictures(baseStamp));
        return baseStamps;
    }


}

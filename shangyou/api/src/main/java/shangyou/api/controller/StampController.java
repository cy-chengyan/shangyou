package shangyou.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import shangyou.core.controller.SubStampController;
import shangyou.core.model.SubStamp;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/stamp")
public class StampController {

    @Autowired
    private SubStampController subStampController;

    @ResponseBody
    @RequestMapping(value = "/sub-stamp/{stid}", method = {RequestMethod.GET})
    public List<SubStamp> querySubStamps(@PathVariable("stid")String stid) {
        if (StringUtils.isEmpty(stid)) {
            return new ArrayList<>();
        }
        return subStampController.queryByStampId(stid);
    }

}

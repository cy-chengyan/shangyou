package shangyou.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import shangyou.core.controller.SubStampController;
import shangyou.core.model.SubStamp;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/stamp")
@Api(value = "Stamp", tags = {"邮票"})
public class StampController {

    @Autowired
    private SubStampController subStampController;

    @ApiOperation(value = "子邮票", notes = "子邮票", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/sub-stamp/{stid}", method = {RequestMethod.GET})
    public List<SubStamp> querySubStamps(@PathVariable("stid") String stid) {
        if (StringUtils.isEmpty(stid)) {
            return new ArrayList<>();
        }
        return subStampController.queryByStampId(stid);
    }

}

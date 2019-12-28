package shangyou.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import shangyou.api.model.SApiRequest;
import shangyou.api.model.SApiResponse;
import shangyou.api.model.req.StampIdRequestData;
import shangyou.api.model.req.StampListRequestData;
import shangyou.core.common.ErrMsg;
import shangyou.core.controller.BaseStampController;
import shangyou.core.controller.RecomController;
import shangyou.core.controller.StampDetailController;
import shangyou.core.controller.SubStampController;
import shangyou.core.model.BaseStamp;
import shangyou.core.model.StampDetail;
import shangyou.core.model.SubStamp;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/stamp")
@Api(value = "Stamp", tags = {"邮票"})
public class StampController {

    @Autowired
    private SubStampController subStampController;
    @Autowired
    private BaseStampController baseStampController;
    @Autowired
    private StampDetailController stampDetailController;
    @Autowired
    private RecomController recomController;

    @ApiOperation(value = "子邮票", notes = "子邮票", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/sub/{stid}", method = {RequestMethod.POST})
    public List<SubStamp> querySubStamps(@PathVariable("stid") String stid, @RequestBody @Valid SApiRequest request) {
        if (StringUtils.isEmpty(stid)) {
            return new ArrayList<>();
        }
        return subStampController.queryByStampId(stid);
    }

    @ApiOperation(value = "邮票基本信息", notes = "邮票基本信息", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/base/{stid}", method = {RequestMethod.POST})
    public SApiResponse<BaseStamp> queryBaseStamps(@PathVariable("stid") String stid, @RequestBody @Valid SApiRequest request) {
        BaseStamp baseStamp = baseStampController.queryBaseStampByStampId(stid);
        if (baseStamp == null) {
            return new SApiResponse<>(baseStampController.getLastErrCode(), baseStampController.getLastErrMsg());
        }
        return new SApiResponse<>(ErrMsg.RC_OK, baseStamp);
    }

    @ApiOperation(value = "邮票详情", notes = "邮票详情", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/detail/{stid}", method = {RequestMethod.POST})
    public SApiResponse<StampDetail> queryStampDetail(@PathVariable("stid") String stid, @RequestBody @Valid SApiRequest request) {
        StampDetail stampDetail = stampDetailController.queryStampDetailByStampId(stid);
        if (stampDetail == null) {
            return new SApiResponse<>(stampDetailController.getLastErrCode(), stampDetailController.getLastErrMsg());
        }
        return new SApiResponse<>(ErrMsg.RC_OK, stampDetail);
    }

    @ApiOperation(value = "邮票列表", notes = "根据不同的条件显示邮票列表", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    public SApiResponse<List<BaseStamp>> queryStampList(@RequestBody @Valid SApiRequest<StampListRequestData> request) {
        StampListRequestData requestData = request.getData();
        int type = requestData.getType();
        int year = requestData.getYear();
        int offset = requestData.getOffset();
        int size = requestData.getSize();
        List<BaseStamp> baseStamps = baseStampController.queryBaseStampList(type, year, offset, size);
        return new SApiResponse<>(ErrMsg.RC_OK, baseStamps);
    }

    @ApiOperation(value = "相关邮票列表", notes = "获取和某张邮票相似的邮票", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/sim", method = {RequestMethod.POST})
    public SApiResponse<List<BaseStamp>> querySimilarList(@RequestBody @Valid SApiRequest<StampIdRequestData> request) {
        String stid = request.getData().getStid();
        List<BaseStamp> baseStamps = recomController.querySimilars(stid);
        return new SApiResponse<>(ErrMsg.RC_OK, baseStamps);
    }

}

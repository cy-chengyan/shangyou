package shangyou.api.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/test")
public class TestController {


    @ResponseBody
    @RequestMapping(value = "/hello", method = {RequestMethod.GET})
    public Map<String, Object> test() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("now", ZonedDateTime.now());
        ret.put("word", "Hello World!");
        return ret;
    }

}

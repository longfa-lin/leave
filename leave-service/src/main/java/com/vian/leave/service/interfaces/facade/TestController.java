package com.vian.leave.service.interfaces.facade;

import com.vian.leave.service.infrastructure.config.Setting;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @Resource
    private Setting setting;

    @GetMapping("/setting")
    public Map getSetting() {
        Map result = new HashMap();
        result.put("uploadAddr", setting.getUploadAddr());
        result.put("path", setting.getPath());
        return result;
    }
}
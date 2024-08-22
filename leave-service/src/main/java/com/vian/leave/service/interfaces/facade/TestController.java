package com.vian.leave.service.interfaces.facade;

import com.vian.leave.service.infrastructure.config.Setting;
import com.vian.leave.service.infrastructure.rabbitmq.direct.Routing;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class TestController {
    @Resource
    private Setting setting;

    @Nonnull
    private final Routing routing;

    @GetMapping("/setting")
    public Map getSetting() {
        Map result = new HashMap();
        result.put("uploadAddr", setting.getUploadAddr());
        result.put("path", setting.getPath());
        return result;
    }

    @GetMapping("/testDirectRouting")
    public String testDirectRouting() {
        routing.routingFirst();
        return "success";
    }
}
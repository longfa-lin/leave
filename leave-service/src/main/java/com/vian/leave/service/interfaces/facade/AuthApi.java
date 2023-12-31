package com.vian.leave.service.interfaces.facade;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.vian.leave.service.application.service.LoginApplicationService;
import com.vian.leave.service.infrastructure.common.api.Response;
import com.vian.leave.service.interfaces.assembler.PersonAssembler;
import com.vian.leave.service.interfaces.dto.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthApi {

    @Autowired
    LoginApplicationService loginApplicationService;

    @PostMapping("/login")
    @SentinelResource(value = "login", blockHandler = "handleException", fallback = "fallbackHandler")
    public Response login(@RequestBody PersonDTO personDTO) {
        try {
            return loginApplicationService.login(PersonAssembler.toDO(personDTO));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * //handleException配置后，fallback配置会失效
     *
     * @param personDTO:
     * @param exception:
     * @return com.vian.leave.service.infrastructure.common.api.Response
     * @methodName handleException
     * @author: lin long fa
     * @date: 2023/12/18 11:45
     **/
    public Response handleException(PersonDTO personDTO, BlockException exception) {
        log.info("flow exception:{}", exception.getClass().getCanonicalName());
        return Response.failed("达到阈值了，请休息一会儿");
    }

    /**
     * 自定义熔断异常
     * 返回值和参数要跟目标函数一样
     */
    public Response fallbackHandler(String productCode) {
        return Response.failed("服务被熔断了，不要调用!");
    }
}

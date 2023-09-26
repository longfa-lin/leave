package com.vian.leave.service.infrastructure.client;

import com.vian.leave.service.domain.person.entity.Person;
import com.vian.leave.service.infrastructure.common.api.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth-service", path = "/demo/auth")
public interface AuthFeignClient {

    @PostMapping(value = "/login")
    Response login(Person person);
}

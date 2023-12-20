package com.vian.leave.service.infrastructure.client;

import com.vian.leave.service.domain.person.entity.Person;
import com.vian.leave.service.infrastructure.common.api.Response;
import com.vian.leave.service.infrastructure.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth-service", path = "/auth", configuration = FeignConfiguration.class)
public interface AuthFeignClient {

    @PostMapping(value = "/login")
    Response login(Person person);
}

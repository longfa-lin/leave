package com.vian.leave.service.application.service;

import com.vian.leave.service.domain.person.entity.Person;
import com.vian.leave.service.domain.person.service.PersonDomainService;
import com.vian.leave.service.infrastructure.client.AuthFeignClient;
import com.vian.leave.service.infrastructure.common.api.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginApplicationService {

    @Autowired
    AuthFeignClient authService;
    @Autowired
    PersonDomainService personDomainService;

    public Response login(Person person) {
        //调用鉴权微服务
        return authService.login(person);
    }
}
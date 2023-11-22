package com.vian.auth.service.interfaces.facade;

import com.vian.auth.service.domain.person.entity.Person;
import com.vian.auth.service.infrastructure.common.api.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @ClassName: AuthApi
 * @Author: lin long fa
 * @Date: 2023-11-21 18:05:04
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthApi {

    @PostMapping("login")
    public Response login(@RequestBody Person person) {
        return Response.ok(person.getPersonName() + ",登录成功！");
    }
}

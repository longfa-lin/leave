package com.vian.leave.service.interfaces.facade;

import com.vian.leave.service.application.service.LoginApplicationService;
import com.vian.leave.service.infrastructure.common.api.Response;
import com.vian.leave.service.interfaces.assembler.PersonAssembler;
import com.vian.leave.service.interfaces.dto.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Response login(PersonDTO personDTO){
        try {
			return loginApplicationService.login(PersonAssembler.toDO(personDTO));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}

package com.vian.auth.service.domain.user.service.register;

import com.vian.auth.service.domain.user.repository.facade.RegisteredClientRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @ClassName: RegisteredClientDomainService
 * @Author: lin long fa
 * @Date: 2024-03-16 18:46:01
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class RegisteredClientDomainService {
    @NonNull
    private RegisteredClientRepository registeredClientRepository;
}

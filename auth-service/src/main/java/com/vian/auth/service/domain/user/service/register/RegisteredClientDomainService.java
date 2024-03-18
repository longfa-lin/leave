package com.vian.auth.service.domain.user.service.register;

import com.mybatisflex.core.query.QueryWrapper;
import com.vian.auth.service.domain.user.entity.RegisteredClientEntity;
import com.vian.auth.service.domain.user.repository.facade.RegisteredClientRepository;
import com.vian.auth.service.domain.user.repository.po.RegisteredClientPO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;

import static com.vian.auth.service.domain.user.repository.po.table.RegisteredClientPOTableDef.REGISTERED_CLIENT_P_O;

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

    public void saveClient(RegisteredClientEntity registeredClientEntity) {
        RegisteredClientPO registeredClientPO = RegisteredClientConvert.INSTANCE.entityToPO(registeredClientEntity);
        registeredClientRepository.saveClient(registeredClientPO);
    }

    public RegisteredClientEntity getOneById(String id) {
        RegisteredClientPO oneById = registeredClientRepository.getOneById(id);
        return RegisteredClientConvert.INSTANCE.POToEntity(oneById);
    }

    public RegisteredClientEntity getOneByClientId(String clientId) {
        RegisteredClientPO oneByClientId = registeredClientRepository.getOneByClientId(clientId);
        return RegisteredClientConvert.INSTANCE.POToEntity(oneByClientId);
    }
}

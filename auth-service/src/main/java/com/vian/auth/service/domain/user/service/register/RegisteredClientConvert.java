package com.vian.auth.service.domain.user.service.register;

import com.vian.auth.service.domain.user.repository.po.RegisteredClientPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

/**
 * @Description: TODO
 * @InterfaceName: RegisteredClientConvert
 * @Author: lin long fa
 * @Date: 2024-03-18 00:01:38
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Mapper
public interface RegisteredClientConvert {
    RegisteredClientConvert INSTANCE = Mappers.getMapper(RegisteredClientConvert.class);

    RegisteredClient POToEntity(RegisteredClientPO registeredClientPO);
}

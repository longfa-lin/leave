package com.vian.auth.service.domain.user.repository.persistence;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.vian.auth.service.domain.user.repository.po.RegisteredClientPO;
import com.vian.auth.service.domain.user.repository.mapper.RegisteredClientDao;
import com.vian.auth.service.domain.user.repository.facade.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import static com.vian.auth.service.domain.user.repository.po.table.RegisteredClientPOTableDef.REGISTERED_CLIENT_P_O;

/**
 * oauth2 客户端注册登记 服务层实现。
 *
 * @author linlo
 * @since 2024-03-13
 */
@Service
public class RegisteredClientRepositoryImpl extends ServiceImpl<RegisteredClientDao, RegisteredClientPO> implements RegisteredClientRepository {
    @Override
    public RegisteredClientPO getOneByClientId(String clientId) {
        QueryWrapper queryWrapper = QueryWrapper.create().from(REGISTERED_CLIENT_P_O)
                .select()
                .where(REGISTERED_CLIENT_P_O.CLIENT_ID.eq(clientId));
        return this.getOne(queryWrapper);
    }

    @Override
    public String saveClient(RegisteredClientPO clientPO) {
        // check exist
        RegisteredClientPO oneByClientId = this.getOneByClientId(clientPO.getClientId());
        if (oneByClientId != null) {
            return oneByClientId.getId();
        }
        this.save(clientPO);

        return clientPO.getId();
    }

    @Override
    public RegisteredClientPO getOneById(String id) {
        return this.getById(id);
    }
}

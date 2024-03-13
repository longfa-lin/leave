package com.vian.auth.service.domain.user.repository.persistence;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.vian.auth.service.domain.user.repository.po.RegisteredClientPO;
import com.vian.auth.service.domain.user.repository.mapper.RegisteredClientDao;
import com.vian.auth.service.domain.user.repository.facade.RegisteredClientRepository;
import org.springframework.stereotype.Service;

/**
 * oauth2 客户端注册登记 服务层实现。
 *
 * @author linlo
 * @since 2024-03-13
 */
@Service
public class RegisteredClientRepositoryImpl extends ServiceImpl<RegisteredClientDao, RegisteredClientPO> implements RegisteredClientRepository {

}

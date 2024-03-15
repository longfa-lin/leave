package com.vian.auth.service.domain.user.repository.persistence;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.vian.auth.service.domain.user.repository.po.UserPO;
import com.vian.auth.service.domain.user.repository.mapper.UserDao;
import com.vian.auth.service.domain.user.repository.facade.UserRepository;
import org.springframework.stereotype.Service;

/**
 * 用户表 服务层实现。
 *
 * @author linlo
 * @since 2024-03-13
 */
@Service
public class UserRepositoryImpl extends ServiceImpl<UserDao, UserPO> implements UserRepository {

    @Override
    public UserPO findOneByPhone(String phone) {
        return null;
    }

    @Override
    public UserPO findOneByUserName(String userName) {
        return null;
    }
}

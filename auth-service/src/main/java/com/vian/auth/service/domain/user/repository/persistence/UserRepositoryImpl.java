package com.vian.auth.service.domain.user.repository.persistence;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.vian.auth.service.domain.user.repository.facade.UserRepository;
import com.vian.auth.service.domain.user.repository.mapper.UserDao;
import com.vian.auth.service.domain.user.repository.po.UserPO;
import org.springframework.stereotype.Service;

import static com.vian.auth.service.domain.user.repository.po.table.UserPOTableDef.USER_P_O;

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
        QueryWrapper queryWrapper = QueryWrapper.create().from(USER_P_O)
                .select()
                .where(USER_P_O.PHONE.eq(phone));
        return this.getOne(queryWrapper);
    }

    @Override
    public UserPO findOneByUserName(String userName) {
        QueryWrapper queryWrapper = QueryWrapper.create().from(USER_P_O)
                .select()
                .where(USER_P_O.NAME.eq(userName));
        return this.getOne(queryWrapper);
    }
}

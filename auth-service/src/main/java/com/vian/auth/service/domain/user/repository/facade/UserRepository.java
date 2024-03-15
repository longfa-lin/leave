package com.vian.auth.service.domain.user.repository.facade;

import com.mybatisflex.core.service.IService;
import com.vian.auth.service.domain.user.repository.po.UserPO;

/**
 * 用户表 服务层。
 *
 * @author linlo
 * @since 2024-03-13
 */
public interface UserRepository {

    /**
     * 手机号查询用户详情
     *
     * @param phone
     * @return
     */
    UserPO findOneByPhone(String phone);

    /**
     * 用户名查询用户详情
     *
     * @param userName
     * @return
     */
    UserPO findOneByUserName(String userName);


}

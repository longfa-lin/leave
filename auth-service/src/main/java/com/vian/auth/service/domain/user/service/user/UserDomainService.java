package com.vian.auth.service.domain.user.service.user;

import com.vian.auth.service.domain.user.entity.User;
import com.vian.auth.service.domain.user.repository.facade.UserRepository;
import com.vian.auth.service.domain.user.repository.po.UserPO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @ClassName: UserDomainService
 * @Author: lin long fa
 * @Date: 2024-03-16 18:39:50
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class UserDomainService {

    @NonNull
    private UserRepository userRepository;

    public User findOneByPhone(String username) {
        UserPO userPO = userRepository.findOneByPhone(username);
        return UserConvert.INSTANCE.POToEntity(userPO);
    }
}

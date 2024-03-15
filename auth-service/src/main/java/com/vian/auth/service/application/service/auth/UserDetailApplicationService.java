package com.vian.auth.service.application.service.auth;

import com.vian.auth.service.domain.user.entity.User;
import com.vian.auth.service.domain.user.service.user.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: TODO
 * @ClassName: AuthApplicationService
 * @Author: lin long fa
 * @Date: 2024-03-16 18:27:15
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Component
@RequiredArgsConstructor
public class UserDetailApplicationService implements UserDetailsService {

    private final UserDomainService userDomainService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //如今这个世界 我们肯定都用手机号登录的了
        User user = userDomainService.findOneByPhone(username);
        if (user == null) {
            throw new UsernameNotFoundException("手机号错误或不存在!");
        }
        //todo 后续可自行修改和完善
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList("/oauth2/token", "/oauth2/authorize","/authorized");
        user.setUsername(username);
        user.setAuthorities(authorityList);
//        user.setId(sysUser.getId());
//        user.setAvatar(sysUser.getAvatar());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setStatus(sysUser.getStatus());
//        user.setPhone(sysUser.getPhone());
        return user;
    }
}

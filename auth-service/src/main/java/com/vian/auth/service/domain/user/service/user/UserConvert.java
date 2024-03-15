package com.vian.auth.service.domain.user.service.user;

import com.vian.auth.service.domain.user.entity.User;
import com.vian.auth.service.domain.user.repository.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description: TODO
 * @ClassName: UserConvert
 * @Author: lin long fa
 * @Date: 2024-03-17 23:48:30
 * @Version: v0.0.1
 * @Edit: Number Date User Remark
 **/
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    User POToEntity(UserPO userPO);

}

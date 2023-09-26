package com.vian.leave.service.domain.person.repository.mapper;

import com.mybatisflex.core.BaseMapper;
import com.vian.leave.service.domain.person.repository.po.PersonPO;
import org.apache.ibatis.annotations.Select;

public interface PersonDao extends BaseMapper<PersonPO> {

    @Select(value = "select p from PersonPO  p where p.relationshipPO.personId=?1")
    PersonPO findLeaderByPersonId(String personId);
}

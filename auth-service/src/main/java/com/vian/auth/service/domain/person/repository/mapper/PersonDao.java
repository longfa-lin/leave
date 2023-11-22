package com.vian.auth.service.domain.person.repository.mapper;

import com.mybatisflex.core.BaseMapper;
import com.vian.auth.service.domain.person.repository.po.PersonPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PersonDao extends BaseMapper<PersonPO> {

    @Select(value = "select * from person p where p.person_id=(" +
            "SELECT leader_id FROM relationship WHERE p.person_id=#{personId})")
    PersonPO findLeaderByPersonId(@Param("personId") Long personId);
}

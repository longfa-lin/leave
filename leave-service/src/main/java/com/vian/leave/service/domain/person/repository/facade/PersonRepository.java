package com.vian.leave.service.domain.person.repository.facade;


import com.vian.leave.service.domain.person.repository.po.PersonPO;

public interface PersonRepository {

    void insert(PersonPO personPO);

    void update(PersonPO personPO);

    PersonPO findById(String id);

    PersonPO findLeaderByPersonId(String personId);

}
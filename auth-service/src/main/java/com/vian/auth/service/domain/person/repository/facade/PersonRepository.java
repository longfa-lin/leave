package com.vian.auth.service.domain.person.repository.facade;


import com.vian.auth.service.domain.person.repository.po.PersonPO;

public interface PersonRepository {

    void insert(PersonPO personPO);

    void update(PersonPO personPO);

    PersonPO findById(Long id);

    PersonPO findLeaderByPersonId(Long personId);

}
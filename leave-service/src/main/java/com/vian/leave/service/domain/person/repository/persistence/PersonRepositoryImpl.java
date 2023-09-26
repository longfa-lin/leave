package com.vian.leave.service.domain.person.repository.persistence;


import com.vian.leave.service.domain.person.repository.facade.PersonRepository;
import com.vian.leave.service.domain.person.repository.mapper.PersonDao;
import com.vian.leave.service.domain.person.repository.po.PersonPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    @Autowired
    PersonDao personDao;

    @Override
    public void insert(PersonPO personPO) {
        personDao.insert(personPO);
    }

    @Override
    public void update(PersonPO personPO) {
        personDao.insert(personPO);
    }

    @Override
    public PersonPO findById(String id) {
//        return personDao.findById(id).orElseThrow(() -> new RuntimeException("未找到用户"));
        return personDao.selectOneById(id);
    }

    @Override
    public PersonPO findLeaderByPersonId(String personId) {
        return personDao.findLeaderByPersonId(personId);
    }

}
package com.vian.leave.service.domain.person.service;

import com.vian.leave.service.domain.person.entity.Person;
import com.vian.leave.service.domain.person.repository.facade.PersonRepository;
import com.vian.leave.service.domain.person.repository.po.PersonPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class PersonDomainService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonFactory personFactory;


    public void create(Person person) {
        PersonPO personPO = personRepository.findById(person.getPersonId());
        if (null == personPO) {
            throw new RuntimeException("Person already exists");
        }
        person.create();
        personRepository.insert(personFactory.createPersonPO(person));
    }

    public void update(Person person) {
        person.setLastModifyTime(new Date());
        personRepository.update(personFactory.createPersonPO(person));
    }

    public void deleteById(Long personId) {
        PersonPO personPO = personRepository.findById(personId);
        Person person = personFactory.getPerson(personPO);
        person.disable();
        personRepository.update(personFactory.createPersonPO(person));
    }

    public Person findById(Long userId) {
        PersonPO personPO = personRepository.findById(userId);
        return personFactory.getPerson(personPO);
    }

    /**
     * find leader with applicant, if leader level bigger then leaderMaxLevel return null, else return Approver from leader;
     *
     * @param applicantId
     * @param leaderMaxLevel
     * @return
     */
    public Person findFirstApprover(Long applicantId, Integer leaderMaxLevel) {
        PersonPO leaderPO = personRepository.findLeaderByPersonId(applicantId);
        if (leaderPO.getRoleLevel() > leaderMaxLevel) {
            return null;
        } else {
            return personFactory.createPerson(leaderPO);
        }
    }

    /**
     * find leader with current approver, if leader level bigger then leaderMaxLevel return null, else return Approver from leader;
     *
     * @param currentApproverId
     * @param leaderMaxLevel
     * @return
     */
    public Person findNextApprover(Long currentApproverId, int leaderMaxLevel) {
        PersonPO leaderPO = personRepository.findLeaderByPersonId(currentApproverId);
        if (leaderPO.getRoleLevel() > leaderMaxLevel) {
            return null;
        } else {
            return personFactory.createPerson(leaderPO);
        }
    }

}
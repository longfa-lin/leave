package com.vian.leave.service.application.service;

import com.vian.leave.service.domain.person.entity.Person;
import com.vian.leave.service.domain.person.service.PersonDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonApplicationService {

    @Autowired
    PersonDomainService personDomainService;

    public void create(Person person) {
        personDomainService.create(person);
    }

    public void update(Person person) {
        personDomainService.update(person);
    }

    public void deleteById(Long personId) {
        personDomainService.deleteById(personId);
    }

    public Person findById(Long personId) {
        return personDomainService.findById(personId);
    }

    public Person findFirstApprover(Long applicantId, Integer leaderMaxLevel) {
        return personDomainService.findFirstApprover(applicantId, leaderMaxLevel);
    }


}

package com.vian.auth.service.domain.person.repository.po;


import com.mybatisflex.annotation.*;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.vian.auth.service.domain.person.entity.valueobject.PersonStatus;
import com.vian.auth.service.domain.person.entity.valueobject.PersonType;
import lombok.Data;

import java.util.Date;

@Data
@Table("person")
public class PersonPO {

    @Id(keyType = KeyType.Generator, value = KeyGenerators.flexId)
    private Long personId;
    private String personName;
    private Long departmentId;
    private PersonType personType;
    private int roleLevel;
    private Date createTime;
    private Date lastModifyTime;
    private PersonStatus status;

    @Column(ignore = true)
    private String leaderId;
    @Column(ignore = true)
    private RelationshipPO relationshipPO;
}

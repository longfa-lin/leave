package com.vian.leave.service.domain.person.repository.po;


import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.vian.leave.service.domain.person.entity.valueobject.PersonStatus;
import com.vian.leave.service.domain.person.entity.valueobject.PersonType;
import lombok.Data;


import java.util.Date;

@Data
@Table("person")
public class PersonPO {

    @Id(keyType = KeyType.Auto)
    String personId;
    String personName;
    String departmentId;
    PersonType personType;


    int roleLevel;
    Date createTime;
    Date lastModifyTime;
    PersonStatus status;

    @Column(ignore = true)
    String leaderId;
    @Column(ignore = true)
    RelationshipPO relationshipPO;
}

package com.vian.leave.service.domain.person.entity;

import lombok.Data;

@Data
public class Relationship {

    String id;
    String personId;
    String leaderId;
    int leaderLevel;
}

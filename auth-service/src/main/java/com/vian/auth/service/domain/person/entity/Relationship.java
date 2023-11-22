package com.vian.auth.service.domain.person.entity;

import lombok.Data;

@Data
public class Relationship {

    private Long id;
    private Long personId;
    private Long leaderId;
    private Integer leaderLevel;
}

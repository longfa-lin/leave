package com.vian.auth.service.infrastructure.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {

    Status status;
    String msg;
    Object data;

    public static Response ok() {
        return Response.builder().status(Status.SUCCESS).msg("成功").build();
    }

    public static Response ok(Object data) {
        return Response.builder().status(Status.SUCCESS).msg("成功").data(data).build();
    }

    public static Response failed(String msg) {
        return Response.builder().status(Status.FAILED).msg(msg).build();
    }

    public enum Status {
        SUCCESS, FAILED
    }
}

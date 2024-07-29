package com.tantanmen.carbofootprint.domain.member.web.dto;

import lombok.Data;

public class LoginRequestDto {

    @Data
    public static class Request {
        private String loginId;
        private String password;
    }
}

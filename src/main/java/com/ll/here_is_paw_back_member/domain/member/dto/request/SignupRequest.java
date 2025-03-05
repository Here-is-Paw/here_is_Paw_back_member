package com.ll.here_is_paw_back_member.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SignupRequest(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String nickname
) {
}

package com.ll.here_is_paw_back_member.domain.member.dto.response;

import lombok.NonNull;

public record LoginResponse(
        @NonNull MemberInfoDto item,
        @NonNull String apiKey,
        @NonNull String accessToken
) {
}

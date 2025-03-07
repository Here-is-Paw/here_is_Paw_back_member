package com.ll.here_is_paw_back_member.domain.member.dto.request;

import jakarta.validation.constraints.NotNull;

public record RadiusRequest(@NotNull Double radius) {
}

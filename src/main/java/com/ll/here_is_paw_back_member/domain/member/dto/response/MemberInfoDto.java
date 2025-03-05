package com.ll.here_is_paw_back_member.domain.member.dto.response;


import com.ll.here_is_paw_back_member.domain.member.entity.Member;
import jakarta.validation.constraints.NotNull;

public record MemberInfoDto(@NotNull Long id, @NotNull String nickname, String avatar) {
    public MemberInfoDto(Member member) {
        this(member.getId(), member.getNickname(), member.getAvatar());
    }
}
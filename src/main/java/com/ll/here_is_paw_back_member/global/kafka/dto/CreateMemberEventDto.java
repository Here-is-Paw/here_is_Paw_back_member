package com.ll.here_is_paw_back_member.global.kafka.dto;

import com.ll.here_is_paw_back_member.domain.member.entity.Member;
import lombok.Data;
import lombok.NonNull;

@Data
public class CreateMemberEventDto {
    private Long id;
    private String nickname;
    private String avatar;
    @NonNull
    private Integer state;

    public CreateMemberEventDto(Member member, int state) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.avatar = member.getAvatar();
        this.state = state;
    }
}

package com.ll.here_is_paw_back_member.domain.member.dto.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record ModifyRequest(@NotNull Long id, MultipartFile profileImage, String nickname){

    public boolean hasProfile() {
        return profileImage != null;
    }

    public boolean hasNickname() {
        return nickname != null;
    }

}


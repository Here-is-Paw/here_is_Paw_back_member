package com.ll.here_is_paw_back_member.global.enums;

import lombok.Getter;

@Getter
public enum PostMethode {
    CREATE(0), PATH(1), DELETE(2);

    // Getter 메서드
    private final int code;

    PostMethode(int code) {
        this.code = code;
    }
}

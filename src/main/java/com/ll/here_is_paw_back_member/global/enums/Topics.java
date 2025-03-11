package com.ll.here_is_paw_back_member.global.enums;

import lombok.Getter;

@Getter
public enum Topics {
    DOG_FACE("members");

    // Getter 메서드
    private final String topicName;

    Topics(String topicName) {
        this.topicName = topicName;
    }
}

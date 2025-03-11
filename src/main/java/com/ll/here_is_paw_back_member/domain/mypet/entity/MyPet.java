package com.ll.here_is_paw_back_member.domain.mypet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ll.here_is_paw_back_member.domain.member.entity.Member;
import com.ll.here_is_paw_back_member.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MyPet extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String breed;

    private String pathUrl;

    private String color;

    private String serialNumber;

    private Integer gender;

    private Integer neutered;

    private Integer age;

    @Column(columnDefinition = "TEXT")
    private String etc;
}
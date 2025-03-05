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

    private String imageUrl;

    private String color;

    private String serialNumber;

    private int gender = 0;

    private boolean neutered;

    private int age;

    @Column(columnDefinition = "TEXT")
    private String etc;
}
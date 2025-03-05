package com.ll.here_is_paw_back_member.domain.mypet.repository;


import com.ll.here_is_paw_back_member.domain.member.entity.Member;
import com.ll.here_is_paw_back_member.domain.mypet.entity.MyPet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPetRepository extends JpaRepository<MyPet, Long> {
    List<MyPet> findAllByMember(Member loginUser);
}

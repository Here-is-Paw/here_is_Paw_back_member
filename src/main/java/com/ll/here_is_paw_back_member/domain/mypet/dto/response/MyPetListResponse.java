package com.ll.here_is_paw_back_member.domain.mypet.dto.response;


import com.ll.here_is_paw_back_member.domain.mypet.entity.MyPet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MyPetListResponse {
    private long id;
    private String name;
    private String breed;
    private int age;

//    @Column(nullable = false)
    private String imageUrl;

    public MyPetListResponse(MyPet myPet) {
        MyPetListResponse dto = new MyPetListResponse();
        dto.setId(myPet.getId());
        dto.setName(myPet.getName());
        dto.setBreed(myPet.getBreed());
        dto.setAge(myPet.getAge());
        dto.setImageUrl(myPet.getPathUrl());
    }

}

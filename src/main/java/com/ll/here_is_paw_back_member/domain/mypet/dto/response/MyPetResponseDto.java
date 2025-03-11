package com.ll.here_is_paw_back_member.domain.mypet.dto.response;


import com.ll.here_is_paw_back_member.domain.mypet.entity.MyPet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MyPetResponseDto {
    private long id;
    private String name;

    private String breed;

//    @Column(nullable = false)
    private String pathUrl;

    private String color;
    private String serialNumber;
    private int gender;
    private int neutered;
    private int age;
    private String etc;

    public static MyPetResponseDto of(MyPet myPet) {
        MyPetResponseDto dto = new MyPetResponseDto();
        dto.setId(myPet.getId());
        dto.setName(myPet.getName());
        dto.setBreed(myPet.getBreed());
        dto.setColor(myPet.getColor());
        dto.setSerialNumber(myPet.getSerialNumber());
        dto.setGender(myPet.getGender());
        dto.setNeutered(myPet.getNeutered());
        dto.setAge(myPet.getAge());
        dto.setEtc(myPet.getEtc());
        dto.setPathUrl(myPet.getPathUrl());
        return dto;
    }

}

package com.ll.here_is_paw_back_member.domain.mypet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

@Getter @Setter
public class MyPetRequest {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "견종은 필수 입력 값입니다.")
    private String breed;

//    @Column(nullable = false)
//    private String imageUrl;

    private String color;
    private String serialNumber;

    @Min(value = 0, message = "성별은 0, 1, 2 중 하나여야 합니다.")
    @Max(value = 2, message = "성별은 0, 1, 2 중 하나여야 합니다.")
    private Integer gender;

    @Min(value = 0, message = "중성화여부는 0, 1, 2 중 하나여야 합니다.")
    @Max(value = 2, message = "중성화여부는 0, 1, 2 중 하나여야 합니다.")
    private Integer neutered;
    private Integer age;
    private String etc;

    @Schema(type = "string", format = "binary", description = "반려동물 이미지 파일")
    private MultipartFile imageFile;

    public boolean hasColor() {
        return color != null;
    }

    public boolean hasSerialNumber() {
        return serialNumber != null;
    }

    public boolean hasGender() {
        return gender != null;
    }

    public boolean hasAge() {
        return age != null;
    }

    public boolean hasEtc() {
        return etc != null;
    }

    public boolean hasImageFile() {
        return imageFile != null && !imageFile.isEmpty();
    }

    public Integer getGender() {
        return gender != null ? gender : 0; // null이면 0(정보없음)으로 처리
    }
}

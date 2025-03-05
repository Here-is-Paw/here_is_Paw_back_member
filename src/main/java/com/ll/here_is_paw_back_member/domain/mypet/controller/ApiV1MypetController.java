package com.ll.here_is_paw_back_member.domain.mypet.controller;


import com.ll.here_is_paw_back_member.domain.member.entity.Member;
import com.ll.here_is_paw_back_member.domain.mypet.dto.request.MyPetRequest;
import com.ll.here_is_paw_back_member.domain.mypet.dto.response.MyPetResponseDto;
import com.ll.here_is_paw_back_member.domain.mypet.service.MyPetService;
import com.ll.here_is_paw_back_member.global.error.ErrorCode;
import com.ll.here_is_paw_back_member.global.globalDto.GlobalResponse;
import com.ll.here_is_paw_back_member.global.webMvc.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mypets")
@Tag(name = "마이페이지 - 반려견 API", description = "mypet")
public class ApiV1MypetController {

    private final MyPetService myPetService;

    @Operation(summary = "내 반려견 전체 조회")
    @GetMapping
    public GlobalResponse<?> getPets(@LoginUser Member loginUser) {
        if (loginUser == null) {
            return GlobalResponse.error(ErrorCode.ACCESS_DENIED);
        }

        List<MyPetResponseDto> myPets = myPetService.getMyPets(loginUser);

        return GlobalResponse.success(myPets);
    }

    @Operation(summary = "반려견 등록")
    @PostMapping(consumes = {"multipart/form-data"})
    public GlobalResponse<String> createPet(@LoginUser Member loginUser, @Valid @ModelAttribute MyPetRequest myPetRequest) {
        log.debug(String.valueOf(myPetRequest));

        if (loginUser == null) {
            return GlobalResponse.error(ErrorCode.ACCESS_DENIED);
        }

        if (myPetRequest == null) {
            return GlobalResponse.error(ErrorCode.INVALID_INPUT_VALUE);
        }

        myPetService.createMyPet(loginUser, myPetRequest);

        return GlobalResponse.createSuccess();
    }

    @Operation(summary = "반려견 단건 조회")
    @GetMapping("/{id}")
    public GlobalResponse<?> findByMyPet(@LoginUser Member loginUser, @PathVariable Long id) {

        if (loginUser == null) {
            return GlobalResponse.error(ErrorCode.ACCESS_DENIED);
        }

        MyPetResponseDto myPet = myPetService.findByMyPet(loginUser, id);

        return GlobalResponse.success(myPet);
    }

    @Operation(summary = "반려견 수정")
    @PatchMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public GlobalResponse<String> modifyPet(@LoginUser Member loginUser, @Valid @ModelAttribute MyPetRequest myPetRequest, @PathVariable Long id) {

        if (loginUser == null) {
            return GlobalResponse.error(ErrorCode.ACCESS_DENIED);
        }

        myPetService.modifyMyPet(loginUser, id, myPetRequest);

        return GlobalResponse.success();
    }

    @Operation(summary = "반려견 삭제")
    @DeleteMapping("/{id}")
    public GlobalResponse<String> deletePet(@LoginUser Member loginUser, @PathVariable Long id) {

        if (loginUser == null) {
            return GlobalResponse.error(ErrorCode.ACCESS_DENIED);
        }

        myPetService.deleteMyPet(loginUser, id);

        return GlobalResponse.success();
    }
}

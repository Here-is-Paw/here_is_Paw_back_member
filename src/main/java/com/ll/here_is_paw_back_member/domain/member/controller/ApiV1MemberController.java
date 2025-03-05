package com.ll.here_is_paw_back_member.domain.member.controller;

import com.ll.here_is_paw_back_member.domain.member.dto.request.LoginRequest;
import com.ll.here_is_paw_back_member.domain.member.dto.request.ModifyRequest;
import com.ll.here_is_paw_back_member.domain.member.dto.request.SignupRequest;
import com.ll.here_is_paw_back_member.domain.member.dto.response.LoginResponse;
import com.ll.here_is_paw_back_member.domain.member.dto.response.MemberInfoDto;
import com.ll.here_is_paw_back_member.domain.member.entity.Member;
import com.ll.here_is_paw_back_member.domain.member.service.MemberService;
import com.ll.here_is_paw_back_member.global.error.ErrorCode;
import com.ll.here_is_paw_back_member.global.globalDto.GlobalResponse;
import com.ll.here_is_paw_back_member.global.rq.Rq;
import com.ll.here_is_paw_back_member.global.webMvc.LoginUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class ApiV1MemberController {

    private final MemberService memberService;
    private final Rq rq;

    // 유저 기본 정보 가져오기
    @GetMapping("/me")
    public GlobalResponse<?> me(@LoginUser Member loginUser) {

        if (loginUser == null) {
            // 미로그인시 401 Unauthorized 반환
            return GlobalResponse.error(ErrorCode.ACCESS_DENIED);
        }

        MemberInfoDto userInfo = memberService.me(loginUser);

        return GlobalResponse.success(userInfo);
    }

    // 어드민 - 유저 추가
    // username (= id)의 앞에 admin 입력하여 등록 시 admin 권한 부여
    // username (= id)의 앞에 manager 입력하여 등록 시 manager 권한 부여
    @PostMapping("/signup")
    public GlobalResponse<String> signup(@Valid @RequestBody SignupRequest signupRq) {

        memberService.signup(signupRq);
        return GlobalResponse.createSuccess("회원 생성 완료");
    }

    // 어드민 - 로그인
    @PostMapping("/login")
    public GlobalResponse<LoginResponse> login(@Valid @RequestBody LoginRequest loginRq) {
        LoginResponse loginUser = memberService.login(loginRq);

        return GlobalResponse.success(loginUser);
    }

    @PatchMapping("/radius-update")
    public GlobalResponse<String> radius_update(@LoginUser Member loginUser, Integer radius) {
        memberService.radius_update(loginUser, radius);
        return GlobalResponse.success("로케이션 업데이트");
    }

    @PatchMapping("/modify")
    public GlobalResponse<MemberInfoDto> modify(@LoginUser Member loginUser, ModifyRequest modifyRequest) {
        MemberInfoDto memberInfoDto = memberService.modify(loginUser, modifyRequest);

        return GlobalResponse.success(memberInfoDto);
    }

    // 로그아웃
    @DeleteMapping("/logout")
    public GlobalResponse<String> logout() {
        rq.deleteCookie("accessToken");
        rq.deleteCookie("apiKey");

        return GlobalResponse.success("로그아웃 되었습니다.");
    }
}

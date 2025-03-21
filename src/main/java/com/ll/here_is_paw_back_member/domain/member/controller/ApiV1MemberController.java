package com.ll.here_is_paw_back_member.domain.member.controller;

import com.ll.here_is_paw_back_member.domain.member.dto.request.LoginRequest;
import com.ll.here_is_paw_back_member.domain.member.dto.request.ModifyRequest;
import com.ll.here_is_paw_back_member.domain.member.dto.request.RadiusRequest;
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
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class ApiV1MemberController {

    private final MemberService memberService;
    private final Rq rq;


    @GetMapping("/token/refresh")
    public ResponseEntity<?> refreshToken(
        @RequestParam String apiKey,
        @RequestParam(required = false) String redirectUri) {

        Member member = memberService.findByApiKey(apiKey)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API key"));

        String newAccessToken = memberService.genAccessToken(member);

        // 응답 헤더와 쿠키에 새 토큰 추가
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + apiKey + " " + newAccessToken);

        ResponseCookie accessTokenCookie = ResponseCookie.from("accessToken", newAccessToken)
            .path("/")
            .maxAge(3600)
            .httpOnly(true)
            .secure(true)
            .build();

        // 리다이렉트 URI가 있으면 그 주소로 리다이렉트
        if (redirectUri != null && !redirectUri.isEmpty()) {
            headers.set(HttpHeaders.LOCATION, redirectUri);
            return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .headers(headers)
                .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
                .build();
        }

        // 리다이렉트 URI가 없으면 일반 응답 반환
        return ResponseEntity.ok()
            .headers(headers)
            .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
            .body(Map.of("accessToken", newAccessToken));
    }
//    // Member 서비스의 컨트롤러에 추가
//    @GetMapping("/token/refresh")
//    public ResponseEntity<?> refreshToken(@RequestParam String apiKey) {
//        Member member = memberService.findByApiKey(apiKey)
//            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API key"));
//
//        String newAccessToken = memberService.genAccessToken(member);
//
//        // 응답 헤더와 쿠키에 새 토큰 추가
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer " + apiKey + " " + newAccessToken);
//
//        ResponseCookie accessTokenCookie = ResponseCookie.from("accessToken", newAccessToken)
//            .path("/")
//            .maxAge(3600)
//            .httpOnly(true)
//            .secure(true)
//            .build();
//
//        return ResponseEntity.ok()
//            .headers(headers)
//            .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
//            .body(Map.of("accessToken", newAccessToken));
//    }

    @GetMapping("/{memberId}")
    public MemberInfoDto getMember(@PathVariable Long memberId) {
        log.debug("memberId : {}", memberId);
        Member member = memberService.findById(memberId).get();
        MemberInfoDto memberInfoDto = new MemberInfoDto(member);
        log.debug("member : {}", member);
        return memberInfoDto;
    }


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

    @PatchMapping("/radius")
    public GlobalResponse<String> radius(@LoginUser Member loginUser, @Valid @RequestBody RadiusRequest radiusRequest) {
        log.debug("radius : {}", radiusRequest.radius());

        memberService.radius(loginUser, radiusRequest.radius());
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

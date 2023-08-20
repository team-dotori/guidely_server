package dotori.guidely.controller;

import dotori.guidely.domain.oauth.AuthTokens;
import dotori.guidely.domain.oauth.kakao.KakaoLoginParams;
import dotori.guidely.service.OAuthLoginService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final OAuthLoginService oAuthLoginService;

//    @GetMapping("/kakao")
//    public ResponseEntity<?> getAccessToken(@RequestParam String code) {
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
//
//    }

//    @GetMapping("/kakao/callback")
//    public String getAuthorizationCode(@RequestParam("code") String code) {
//        System.out.println("code = " + code);
//        return code;
//    }
//
//    @PostMapping("/api/auth/kakao")
//    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
//        return ResponseEntity.ok(oAuthLoginService.login(params));
//    }

    @GetMapping("/kakao/callback")
    public ResponseEntity<AuthTokens> loginKakao(@RequestParam("code") String code) {
        KakaoLoginParams params = new KakaoLoginParams(code);

        return ResponseEntity.ok(oAuthLoginService.login(params));
    }
}
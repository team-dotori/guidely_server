package dotori.guidely.domain.oauth.controller;

import dotori.guidely.domain.oauth.dto.AuthTokenDto;
import dotori.guidely.domain.oauth.domain.KakaoLoginParams;
import dotori.guidely.domain.oauth.service.OAuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = RequestMethod.GET)
public class OAuthController {
    private final OAuthService oAuthService;

    @Operation(summary = "accessToken 발급", description = "사용자의 accessToken을 발급하는 API")
    @GetMapping("/kakao/callback")
    public ResponseEntity<AuthTokenDto> loginKakao(@RequestParam("code") String code) {
        KakaoLoginParams params = new KakaoLoginParams(code);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(oAuthService.login(params));
    }
}

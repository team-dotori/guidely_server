package dotori.guidely.domain.user.controller;

import dotori.guidely.domain.badge.dto.BadgeDto;
import dotori.guidely.domain.declaration.dto.response.DeclarationResponseDto;
import dotori.guidely.domain.user.domain.UserType;
import dotori.guidely.domain.user.dto.UserDto;
import dotori.guidely.domain.user.dto.UserTypeDto;
import dotori.guidely.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @Operation(summary = "모든 사용자 조회", description = "가입된 모든 사용자를 조회하는 API")
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(summary = "사용자 유형 조회", description = "사용자의 유형을 조회하는 API")
    @GetMapping("/type")
    public ResponseEntity<UserType> findTypeByAccessToken(@RequestHeader(value = "accessToken") String accessToken) {
        Long userId = userService.findUserIdByAccessToken(accessToken);

        UserDto userDto = userService.findByUserId(userId);
        UserType type = userDto.getType();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(type);
    }

    @Operation(summary = "사용자 유형 설정", description = "사용자의 유형을 설정하는 API")
    @PostMapping("/type")
    public ResponseEntity<String> getUserType(@RequestHeader(value = "accessToken") String accessToken,
                                         @RequestBody UserTypeDto dto) {
        Long userId = userService.findUserIdByAccessToken(accessToken);
        userService.setUserType(userId, dto);

        return ResponseEntity.ok("Type이 설정되었습니다.");
    }

    @Operation(summary = "사용자 보유 뱃지 조회", description = "사용자가 보유 중인 뱃지들을 조회하는 API")
    @GetMapping("/badges")
    public ResponseEntity<List<BadgeDto>> findBadges(@RequestHeader(value = "accessToken") String accessToken){
        Long userId = userService.findUserIdByAccessToken(accessToken);
        return ResponseEntity.ok(userService.findBadges(userId));
    }

    @Operation(summary = "사용자 신고 내역 조회", description = "사용자의 신고 내역을 조회하는 API")
    @GetMapping("/declarations")
    public ResponseEntity<List<DeclarationResponseDto>> findByUserId(@RequestHeader(value = "accessToken") String accessToken){
        Long userId = userService.findUserIdByAccessToken(accessToken);
        return ResponseEntity.ok(userService.findDeclarationList(userId));

    }
}

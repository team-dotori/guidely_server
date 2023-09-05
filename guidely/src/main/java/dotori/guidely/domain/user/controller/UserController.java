package dotori.guidely.domain.user.controller;

import dotori.guidely.domain.badge.dto.BadgeDto;
import dotori.guidely.domain.declaration.dto.response.DeclarationResponseDto;
import dotori.guidely.domain.user.domain.UserType;
import dotori.guidely.domain.user.dto.UserDto;
import dotori.guidely.domain.user.dto.UserTypeDto;
import dotori.guidely.domain.user.service.UserService;
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
@CrossOrigin(origins = "*", methods = RequestMethod.GET)
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/type")
    public ResponseEntity<UserType> findTypeByAccessToken(@RequestHeader(value = "accessToken") String accessToken) {
        Long userId = userService.findUserIdByAccessToken(accessToken);

        UserDto userDto = userService.findByUserId(userId);
        UserType type = userDto.getType();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(type);
    }

    @PostMapping("/type")
    public ResponseEntity<String> getUserType(@RequestHeader(value = "accessToken") String accessToken,
                                         @RequestBody UserTypeDto dto) {
        Long userId = userService.findUserIdByAccessToken(accessToken);
        userService.setUserType(userId, dto);

        return ResponseEntity.ok("Type이 설정되었습니다.");
    }

    @GetMapping("/badges")
    public ResponseEntity<List<BadgeDto>> findBages(@RequestHeader(value = "accessToken") String accessToken){
        Long userId = userService.findUserIdByAccessToken(accessToken);
        return ResponseEntity.ok(userService.findBadges(userId));
    }
    /**
     * user Id 신고 정보 가져오기
     */
    @GetMapping("/declarations")
    public ResponseEntity<List<DeclarationResponseDto>> findByUserId(@RequestHeader(value = "accessToken") String accessToken){
        Long userId = userService.findUserIdByAccessToken(accessToken);
        return ResponseEntity.ok(userService.findDeclarationList(userId));

    }
}

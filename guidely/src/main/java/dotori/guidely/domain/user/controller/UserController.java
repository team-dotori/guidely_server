package dotori.guidely.domain.user.controller;

import dotori.guidely.domain.user.dto.UserDto;
import dotori.guidely.domain.user.service.UserService;
import dotori.guidely.global.utils.jwt.AuthTokensGenerator;
import dotori.guidely.domain.user.domain.User;
import dotori.guidely.domain.user.domain.UserType;
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
    private final AuthTokensGenerator authTokensGenerator;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/entrancePage/token")
    public ResponseEntity<UserType> findTypeByAccessToken(@RequestHeader(value = "accessToken") String accessToken) {
        Long userId = authTokensGenerator.extractUserId(accessToken);


        UserDto userDto = userService.findByUserId(userId);
        UserType type = userDto.getType();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(type);
    }
}

package dotori.guidely.domain.declaration.controller;

import dotori.guidely.domain.declaration.dto.DeclarationDto;
import dotori.guidely.domain.declaration.dto.response.DeclarationResponseDto;
import dotori.guidely.domain.declaration.service.DeclarationService;
import dotori.guidely.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/declaration")
@CrossOrigin(origins = "*")
@Slf4j
public class DeclarationController {
    private final DeclarationService declarationService;
    private final UserService userService;

    /**
     * 신고 정보 저장
     */
    @Operation(summary = "신고 정보 저장", description = "신고 정보를 저장하는 API")
    @PostMapping
    public ResponseEntity<DeclarationResponseDto> save(@RequestHeader(value="accessToken") String accessToken,@RequestBody DeclarationDto declarationDto){
        Long userId = userService.findUserIdByAccessToken(accessToken);
        DeclarationResponseDto declarationResponseDto = declarationService.saveDeclaration(userId,declarationDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(declarationResponseDto);
    }

    /**
     * 신고 정보 모두 가져오기
     */
    @Operation(summary = "모든 신고 정보 조회", description = "등록된 모든 신고 정보를 조회하는 API")
    @GetMapping
    public ResponseEntity<List<DeclarationResponseDto>> findAll(){
        return ResponseEntity
                .ok(declarationService.findAll());
    }

    /**
     * 신고 정보 삭제하기
     */
    @Operation(summary = "신고 정보 삭제", description = "신고 정보를 삭제하는 API")
    @DeleteMapping
    public ResponseEntity<Long> delete(@RequestHeader(value="accessToken") String accessToken){
        Long userId = userService.findUserIdByAccessToken(accessToken);
        return ResponseEntity.ok(declarationService.delete(userId));
    }

    /**
     * 신고 정보 수정하기
     */
    @Operation(summary = "신고 정보 수정", description = "신고 정보를 수정하는 API")
    @PatchMapping
    public ResponseEntity<Long> update(@RequestHeader(value="accessToken")String accessToken,@RequestBody DeclarationDto declarationDto){
        Long userId = userService.findUserIdByAccessToken(accessToken);
        return ResponseEntity.ok(declarationService.update(userId,declarationDto));
    }
}

package dotori.guidely.domain.declaration.controller;

import dotori.guidely.domain.declaration.dto.DeclarationDto;
import dotori.guidely.domain.declaration.dto.response.DeclarationResponseDto;
import dotori.guidely.domain.declaration.service.DeclarationService;
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
    /**
     * 신고 정보 저장
     */
    @PostMapping("{id}")
    public ResponseEntity<DeclarationResponseDto> save(@PathVariable Long id,@RequestBody DeclarationDto declarationDto){ //@RequestHeader(value="accessToken") String accessToken
//        Long userId = authTokensGenerator.extractUserId(accessToken);
//        userService.saveDeclaration(userId,declarationDto); // user의 신고 리스트에 저장
//        userService.saveDeclaration(id, declarationDto);// 임시
        DeclarationResponseDto declarationResponseDto = declarationService.saveDeclaration(id,declarationDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(declarationResponseDto);
    }
    /**
     * 신고 정보 모두 가져오기
     */
    @GetMapping
    public ResponseEntity<List<DeclarationResponseDto>> findAll(){
        return ResponseEntity
                .ok(declarationService.findAll());
    }
    /**
     * 신고 정보 삭제하기
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id){
        return ResponseEntity.ok(declarationService.delete(id));
    }
    /**
     * 신고 정보 수정하기
     */
    @PatchMapping("{id}")
    public ResponseEntity<Long> update(@PathVariable Long id,@RequestBody DeclarationDto declarationDto){
        return ResponseEntity.ok(declarationService.update(id,declarationDto));
    }
    /**
     * 신고 좋아요
     */
    @PatchMapping("/like/{id}")
    public ResponseEntity<Long> addLike(@PathVariable Long id){
        return ResponseEntity.ok(declarationService.addLike(id));
    }
    // TODO : User Id로 Location 참조기능

}

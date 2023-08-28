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
     * 저장
     */
    @PostMapping
    public ResponseEntity<DeclarationResponseDto> save(@RequestBody DeclarationDto declarationDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(declarationService.saveDeclaration(declarationDto));
    }
    /**
     * 신고 정보 모두 가져오기
     */
    @GetMapping
    public ResponseEntity<List<DeclarationResponseDto>> declarationFindAll(){
        return ResponseEntity
                .ok(declarationService.declarationFindAll());
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


    //TODO : 위치 ID로 신고 List조회
    //TODO : User Id로 Location 참조기능

}

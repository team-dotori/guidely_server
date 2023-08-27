package dotori.guidely.domain.declaration.controller;

import dotori.guidely.domain.declaration.domain.Declaration;
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

    @PostMapping
    public ResponseEntity<Declaration> save(@RequestBody DeclarationDto declarationDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(declarationService.saveDeclaration(declarationDto));
    }
    @GetMapping
    public ResponseEntity<List<DeclarationResponseDto>> findAll(){
        return ResponseEntity
                .ok(declarationService.findAll());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id){
        return ResponseEntity.ok(declarationService.delete(id));
    }
    @PatchMapping("{id}")
    public ResponseEntity<Long> update(@PathVariable Long id,@RequestBody DeclarationDto declarationDto){
        return ResponseEntity.ok(declarationService.update(id,declarationDto));
    }
    //TODO : 이미지 받아서 url로 바꾸는 controller저장
}

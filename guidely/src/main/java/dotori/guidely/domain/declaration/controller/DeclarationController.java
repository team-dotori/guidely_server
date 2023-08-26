package dotori.guidely.domain.declaration.controller;

import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.declaration.dto.DeclarationDto;
import dotori.guidely.domain.declaration.service.DeclarationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}

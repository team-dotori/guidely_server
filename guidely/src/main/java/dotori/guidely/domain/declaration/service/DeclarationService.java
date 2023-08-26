package dotori.guidely.domain.declaration.service;

import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.declaration.dto.DeclarationDto;
import dotori.guidely.domain.declaration.repository.DeclarationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
@RequiredArgsConstructor
public class DeclarationService {
    private final DeclarationRepository declarationRepository;

    private final ModelMapper modelMapper;
    @Transactional
    public Declaration saveDeclaration(DeclarationDto declarationDto) {
        return declarationRepository.save(declarationDto.toEntity());
    }


}

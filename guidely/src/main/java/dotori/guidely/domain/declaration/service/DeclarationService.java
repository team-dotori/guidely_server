package dotori.guidely.domain.declaration.service;

import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.declaration.dto.DeclarationDto;
import dotori.guidely.domain.declaration.dto.response.DeclarationResponseDto;
import dotori.guidely.domain.declaration.repository.DeclarationRepository;
import dotori.guidely.exception.CustomException;
import dotori.guidely.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeclarationService {
    private final DeclarationRepository declarationRepository;

    private final ModelMapper modelMapper;
    @Transactional
    public Declaration saveDeclaration(DeclarationDto declarationDto) {
        return declarationRepository.save(declarationDto.toEntity());
    }

    public List <DeclarationResponseDto> findAll(){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<Declaration> declarations = declarationRepository.findAll();
        List<DeclarationResponseDto> responseDtos = new ArrayList<>();
        for(Declaration declaration : declarations){
            responseDtos.add(modelMapper.map(declaration, DeclarationResponseDto.class));
        }
        return responseDtos;
    }
    @Transactional
    public Long delete(Long id){
        declarationRepository.delete(declarationRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.DECLARATION_NOT_FOUND)));
        return id;
    }
    @Transactional
    public Long update(Long id,DeclarationDto params){
        Declaration declaration = declarationRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.DECLARATION_NOT_FOUND));
        declaration.update(params.getRisk(),params.getContents(),params.getImgUrl());
        return id;
    }
}

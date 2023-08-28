package dotori.guidely.domain.declaration.service;

import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.declaration.domain.Location;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeclarationService {
    private final DeclarationRepository declarationRepository;

    private final LocationService locationService;
    private final ModelMapper modelMapper;
    @Transactional
    public DeclarationResponseDto saveDeclaration(DeclarationDto declarationDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Optional<Location> location = locationService.findByCoor(declarationDto.getLatitude(),declarationDto.getLongitude());
        Declaration declarationEntity = declarationDto.toEntity();
        if (location.isEmpty()){ //처음 저장되는 위치라면
            Location newLocation = Location.builder()
                    .address(declarationDto.getAddress())
                    .buildingName(declarationDto.getBuildingName())
                    .latitude(declarationDto.getLatitude())
                    .longitude(declarationDto.getLongitude())
                    .type(declarationDto.getType())
                    .build();
            declarationEntity.setLocation(newLocation);
            newLocation.addDeclaration(declarationEntity);
        }else{
            declarationEntity.setLocation(location.orElseThrow(()-> new CustomException(ErrorCode.INTERNAL_SERVER_ERROR)));
            location.get().getDeclarationList().add(declarationEntity);
        }

        Declaration declaration = declarationRepository.save(declarationEntity);

        return modelMapper.map(declaration,DeclarationResponseDto.class);

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

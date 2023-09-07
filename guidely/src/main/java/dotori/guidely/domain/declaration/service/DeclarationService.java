package dotori.guidely.domain.declaration.service;

import dotori.guidely.domain.badge.scheduler.CollectBadge;
import dotori.guidely.domain.badge.service.BadgeService;
import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.declaration.domain.Location;
import dotori.guidely.domain.declaration.dto.DeclarationDto;
import dotori.guidely.domain.declaration.dto.response.DeclarationResponseDto;
import dotori.guidely.domain.declaration.repository.DeclarationRepository;
import dotori.guidely.domain.user.domain.User;
import dotori.guidely.domain.user.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final LocationService locationService;

    private final BadgeService badgeService;
    private final ModelMapper modelMapper;
    @Transactional
    public DeclarationResponseDto saveDeclaration(long userId, DeclarationDto declarationDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Optional<Location> location = locationService.checkLocationIsExist(declarationDto.getLatitude(),declarationDto.getLongitude());
        Declaration declarationEntity = declarationDto.toEntity();
        User user = userRepository.findByUserId(userId).orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
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
            newLocation.addCountDeclation();
        }else{
            declarationEntity.setLocation(location.orElseThrow(()-> new CustomException(ErrorCode.INTERNAL_SERVER_ERROR)));
            location.get().addDeclaration(declarationEntity);
            location.get().addCountDeclation();
        }
        declarationEntity.setUser(user);
        user.addDeclaration(declarationEntity);
        Declaration declaration = declarationRepository.save(declarationEntity);
        if (user.getBadges().size()==0){
            badgeService.reset(user);
        }
        checkCount(user);
        return new DeclarationResponseDto(declaration);

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
    public long delete(long id){
        declarationRepository.delete(declarationRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.DECLARATION_NOT_FOUND)));
        return id;
    }
    @Transactional
    public long update(long id,DeclarationDto params){
        Declaration declaration = declarationRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.DECLARATION_NOT_FOUND));
        declaration.update(params.getRisk(),params.getContents(),params.getImgUrl());
        return id;
    }

    public void checkCount(User user){
        CollectBadge collectBadge = new CollectBadge(user,5,user.getDeclarationList().size(),1);
        collectBadge.detectAndExecute();
    }
}


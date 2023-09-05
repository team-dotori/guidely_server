package dotori.guidely.domain.user.service;

import dotori.guidely.domain.badge.domain.Badge;
import dotori.guidely.domain.badge.dto.BadgeDto;
import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.declaration.dto.response.DeclarationResponseDto;
import dotori.guidely.domain.user.domain.User;
import dotori.guidely.domain.user.dto.UserDto;
import dotori.guidely.domain.user.dto.UserTypeDto;
import dotori.guidely.domain.user.repository.UserRepository;
import dotori.guidely.exception.CustomException;
import dotori.guidely.exception.ErrorCode;
import dotori.guidely.global.utils.jwt.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;

    private final ModelMapper modelMapper;
    public List<UserDto> findAll() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<User> users = userRepository.findAll();
        List<UserDto> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(UserDto.builder()
                            .oAuthProvider(user.getOAuthProvider())
                            .type(user.getType())
                            .email(user.getEmail())
                            .nickname(user.getNickname())
                            .build());
        }

        return dtos;
    }
    public List<User> list(){
        return userRepository.findAll();
    }

    public UserDto findByUserId(Long userId) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        /**
         * TODO
         * Exception Handler 정의
         */
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException());
        return modelMapper.map(user, UserDto.class);
    }
    public User findByid(Long userId){
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException());
        return user;
    }
    public List<DeclarationResponseDto> findDeclarationList(long userId) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        User user = userRepository.findByUserId(userId).orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));
        List<Declaration> declarationList = user.getDeclarationList();
        List<DeclarationResponseDto> declarationResponseDtos = new ArrayList<>();

        for(Declaration declaration : declarationList){
            declarationResponseDtos.add(DeclarationResponseDto.builder().declaration(declaration).build());
        }
        return declarationResponseDtos;
    }
    public List<BadgeDto> findBadges(long userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        List<Badge> badgeList = user.getBadges();
        List<BadgeDto> badgeDtos = new ArrayList<>();
        for (Badge badge : badgeList) {
            badgeDtos.add(BadgeDto.builder()
                    .level(badge.getLevel())
                    .badgeId(badge.getBadgeId())
                    .collectDate(badge.getCollectDate())
                    .kingBadge(badge.getKingBadge())
                    .state(badge.getState())
                    .build());
        }
        return badgeDtos;
    }

    public Long findUserIdByAccessToken(String accessToken) {
        return authTokensGenerator.extractUserId(accessToken);
    }

    public void setUserType(Long userId, UserTypeDto dto) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        user.setType(dto.getType());
    }
}

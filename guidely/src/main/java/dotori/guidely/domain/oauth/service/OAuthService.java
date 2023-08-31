package dotori.guidely.domain.oauth.service;

import dotori.guidely.domain.oauth.OAuthApiClient;
import dotori.guidely.domain.oauth.OAuthLoginParams;
import dotori.guidely.domain.oauth.domain.OAuthInfoResponse;
import dotori.guidely.domain.oauth.domain.OAuthProvider;
import dotori.guidely.domain.oauth.dto.AuthTokenDto;
import dotori.guidely.domain.user.domain.User;
import dotori.guidely.domain.user.domain.UserType;
import dotori.guidely.domain.user.dto.UserDto;
import dotori.guidely.domain.user.repository.UserRepository;
import dotori.guidely.global.utils.jwt.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService {

    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfo requestOAuthInfo;
    private final ModelMapper modelMapper;

    public AuthTokenDto login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfo.request(params);
        Long userId = findOrCreateUser(oAuthInfoResponse);
        return authTokensGenerator.generate(userId);
    }

    private Long findOrCreateUser(OAuthInfoResponse oAuthInfoResponse) {
        return userRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(User::getUserId)
                .orElseGet(() -> newUser(oAuthInfoResponse));
    }

    private Long newUser(OAuthInfoResponse oAuthInfoResponse) {
        UserDto userDto = UserDto.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .type(UserType.NEW)
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        User user = modelMapper.map(userDto, User.class);
        return userRepository.save(user).getUserId();
    }

    @Component
    static class RequestOAuthInfo {
        private final Map<OAuthProvider, OAuthApiClient> clients;

        public RequestOAuthInfo(List<OAuthApiClient> clients) {
            this.clients = clients.stream().collect(
                    Collectors.toUnmodifiableMap(OAuthApiClient::oAuthProvider, Function.identity())
            );
        }

        public OAuthInfoResponse request(OAuthLoginParams params) {
            OAuthApiClient client = clients.get(params.oAuthProvider());
            String accessToken = client.requestAccessToken(params);
            return client.requestOauthInfo(accessToken);
        }
    }
}

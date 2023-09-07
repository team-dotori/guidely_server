package dotori.guidely.domain.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenDto {
    private Long userId;
    private String accessToken;
    private String refreshToken;
    private String grantType;
    private Long expiresIn;

    public static AuthTokenDto of(Long userId, String accessToken, String refreshToken, String grantType, Long expiresIn) {
        return new AuthTokenDto(userId, accessToken, refreshToken, grantType, expiresIn);
    }
}


package dotori.guidely.domain.user.dto;

import dotori.guidely.domain.oauth.domain.OAuthProvider;
import dotori.guidely.domain.user.domain.UserType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String email;
    private String nickname;
    private UserType type;
    private OAuthProvider oAuthProvider;

    @Builder
    public UserDto(String email, String nickname, UserType type, OAuthProvider oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.type = type;
        this.oAuthProvider = oAuthProvider;
    }
}

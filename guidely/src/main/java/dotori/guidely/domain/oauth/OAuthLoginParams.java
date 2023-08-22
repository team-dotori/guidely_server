package dotori.guidely.domain.oauth;

import dotori.guidely.domain.oauth.domain.OAuthProvider;
import org.springframework.util.MultiValueMap;

public interface OAuthLoginParams {
    OAuthProvider oAuthProvider();

    MultiValueMap<String, String> makeBody();
}

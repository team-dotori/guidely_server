package dotori.guidely.domain.oauth;

import dotori.guidely.domain.oauth.domain.OAuthInfoResponse;
import dotori.guidely.domain.oauth.domain.OAuthProvider;

public interface OAuthApiClient {
    OAuthProvider oAuthProvider();

    String requestAccessToken(OAuthLoginParams params);

    OAuthInfoResponse requestOauthInfo(String accessToken);
}

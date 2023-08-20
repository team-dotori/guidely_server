package dotori.guidely.domain.oauth;

public interface OAuthInfoResponse {
    String getEmail();

    String getNickname();

    OAuthProvider getOAuthProvider();
}

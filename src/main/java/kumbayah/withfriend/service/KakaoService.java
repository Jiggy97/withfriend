package kumbayah.withfriend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KakaoService {
    @Value("${kakao.client.id}")
    private String KAKAO_RESTAPI_KEY;

    @Value("${kakao.client.secret}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${kakao.redirect.url}")
    private String KAKAO_REDIRECT_URL;

    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";
    private final static String KAKAO_API_URI = "https://kapi.kakao.com";

    public String getKakaoLogin() {
        return KAKAO_AUTH_URI + "/oauth/authorize"
                + "?response_type=code&"
                + "client_id=" + KAKAO_RESTAPI_KEY
                + "&redirect_uri=" + KAKAO_REDIRECT_URL;
    }
}

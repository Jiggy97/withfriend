package kumbayah.withfriend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kumbayah.withfriend.dto.KakaoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;



@Service
public class KakaoService {
    @Value("${kakao.client.id}")
    private String KAKAO_RESTAPI_KEY;

    @Value("${kakao.client.secret}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${kakao.redirect.uri}")
    private String KAKAO_REDIRECT_URI;

    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";
    private final static String KAKAO_API_URI = "https://kapi.kakao.com";

    private final KakaoDTO kakaoDTO;

    public KakaoService(KakaoDTO kakaoDTO) {
        this.kakaoDTO = kakaoDTO;
    }

    public String getKakaoLogin() {
        return KAKAO_AUTH_URI + "/oauth/authorize"
                + "?response_type=code&"
                + "client_id=" + KAKAO_RESTAPI_KEY
                + "&redirect_uri=" + KAKAO_REDIRECT_URI;
    }

    public KakaoDTO getKakaoInfo(String code) throws Exception {
        if (code==null) throw new Exception("Failed get authorization code");

        String accessToken = "";
        String refreshToken = "";

        try {
            // kakao oauth2 토큰을 받기 위한 요청을 할 때 헤더의 이름은 Content-type 으로,
            // 데이터 타입은 application/x-www-form-urlencoded 으로 해야함 (charset=utf-8 요청 데이터 타입)
            // 해더 객체 생성, 객체에 Content-type 해더 추가, application/x-www-form-urlencoded 형식의 본문 해석
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            // kakao oauth2 인증에 사용할 파라미터 맵 객체 생성 및 각 맵에 파라미터와 값 추가
            // 해당 파라미터들은 oauth2 인증에 필요한 필수 파라미터
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type"     , "authorization_code");
            params.add("client_id"      , KAKAO_RESTAPI_KEY);
            params.add("client_secret"  , KAKAO_CLIENT_SECRET);
            params.add("code"           , code);
            params.add("redirect_uri"   , KAKAO_REDIRECT_URI);

            // 스프링에서 제공하는 RestTemplate은 HTTP 요청과 응답을 간편하게 처리할 수 있는 클래스이다
            RestTemplate restTemplate = new RestTemplate();
            // HTTP 요청을 통해 토큰을 받기 위한 필수 데이터들을 담은 headers와 params를 HttpEntity 객체로 캡슐화
            HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

            // 토큰을 받기 위한 HTTP 메서드: POST, URL: https://kauth.kakao.com/oauth/token
            // 데이터 : httpEntity 객체,
            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_AUTH_URI + "/oauth/token",
                    HttpMethod.POST,
                    httpEntity,
                    String.class    // 응답 받은 값을 문자렬로 받겠다는 의미
            );

            // 데이터 파싱, 다양한 라이브러리와 코드들이 존재
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            accessToken = jsonNode.get("access_Token").asText();
            refreshToken = jsonNode.get("refresh_Token").asText();
        } catch (Exception e) {
            throw new Exception("API call failed");
        }

        return getUserInfoWithToken(accessToken);
    }

    // 예외 처리에 대해 잘 알지 못해서 사용하지 않으려 했지만 객체를 파싱하는 과정에서 예외처리가 없으면 오류나서 일단 해줌
    private KakaoDTO getUserInfoWithToken(String accessToken) throws Exception {
        // HttpHeader 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer" + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader 담기
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_API_URI + "/v2/user/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        // response data 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        JsonNode account = jsonNode.get("kakao_account");
        JsonNode profile = account.get("profile");

        long id = jsonNode.get("id").asLong();
        String email = account.get("account").asText();
        String nickname = account.get("nickname").asText();
    }
}

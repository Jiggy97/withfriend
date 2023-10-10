package kumbayah.withfriend.service.portone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Service
public class PortOneService {
    @Value("${portone.key}")
    private String restApiKey;
    @Value("${portone.secret}")
    private String restApiSecret;

    public ResponseEntity<String> getToken() {
        // 요청 바디에 API 키와 시크릿을 추가
        String requestJson = "{\"imp_key\": \"" + restApiKey + "\", \"imp_secret\": \"" + restApiSecret + "\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://api.iamport.kr/users/getToken",
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            // 성공적으로 응답 받았을 때 응답 데이터 반환
            return responseEntity;
        } else {
            // 요청이 실패한 경우 예외 처리
            throw new RuntimeException("Iamport API 요청 실패");
        }
    }
}

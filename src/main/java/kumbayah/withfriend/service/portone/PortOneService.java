package kumbayah.withfriend.service.portone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.controller.portone.PortOneController;
import kumbayah.withfriend.entity.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class PortOneService {
    @Value("${portone.key}")
    private String restApiKey;
    @Value("${portone.secret}")
    private String restApiSecret;

    @Autowired
    private PortOneController portOneController;

    public MsgEntity getToken(String impUid, double chargePoint, HttpSession session) throws JsonProcessingException {
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
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
            String accessToken = jsonNode.path("response").path("access_token").asText();
            session.setAttribute("payment_access_token", accessToken);

            double amount = paymentDetail(impUid, accessToken);

            if (amount != chargePoint) return new MsgEntity(false, "결제 금액이 요청 결제 금액과 다릅니다.");

            // 성공적으로 응답 받았을 때 응답 데이터 반환
            return new MsgEntity(true, "결제 요청이 성공적으로 인증되었습니다.");
        } else {
            // 요청이 실패한 경우 예외 처리
            throw new RuntimeException("Iamport API 요청 실패");
        }
    }

    public double paymentDetail(String impUid, String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");

        RestTemplate rtForUserInfo = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = rtForUserInfo.exchange(
                "https://api.iamport.kr/payments/" + impUid,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
            return jsonNode.path("response").path("amount").asDouble();
        } else {
            // 요청이 실패한 경우 예외 처리
            throw new RuntimeException("Iamport API 요청 실패");
        }
    }
}

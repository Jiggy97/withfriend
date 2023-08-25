package kumbayah.withfriend.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import kumbayah.withfriend.dto.KakaoDTO;
import kumbayah.withfriend.entity.MsgEntity;
import kumbayah.withfriend.service.KakaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kakao")
public class KakaoController {

    private final KakaoService kakaoService;

    KakaoController(KakaoService kakaoService) {
        this.kakaoService = kakaoService;
    }

    @RequestMapping("/callback")
    // HttpServletRequest 는 자바 Servlet 에서 제공하는 인터페이스로 웹 어플리케이션이 클라이언트와 HTTP 통신을 하는데 도움을 준다.
    public ResponseEntity<MsgEntity> callback(HttpServletRequest request) throws Exception {
        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"));

        return ResponseEntity.ok().body(new MsgEntity("Success", kakaoInfo));
    }

    @RequestMapping("/disconnect")
    public ResponseEntity<String> disconnectKakao(@RequestParam("access_token") String accessToken) throws Exception {
        try {
            kakaoService.disconnectKakaoAccount(accessToken);
            return ResponseEntity.ok("Kakao account disconnected");
        } catch (Exception e) {
            throw new Exception("Fail disconnect");
        }
    }
}

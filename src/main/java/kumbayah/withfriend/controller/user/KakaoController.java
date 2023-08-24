package kumbayah.withfriend.controller.user;

import jakarta.servlet.http.HttpServlet;
import kumbayah.withfriend.dto.KakaoDTO;
import kumbayah.withfriend.entity.MsgEntity;
import kumbayah.withfriend.service.KakaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kakao")
public class KakaoController {

    private final KakaoService kakaoService;

    KakaoController(KakaoService kakaoService) {
        this.kakaoService = kakaoService;
    }

    @RequestMapping("/callback")
    public ResponseEntity<MsgEntity> callback(HttpServlet request) {
        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request);
    }
}

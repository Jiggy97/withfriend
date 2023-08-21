package kumbayah.withfriend.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KakaoLoginController {
    @GetMapping("/user/kakao/callback")
    public String KakaoLogin(String code) {
        // code는 카카오 서버로부터 받은 인가 코드

    }

}

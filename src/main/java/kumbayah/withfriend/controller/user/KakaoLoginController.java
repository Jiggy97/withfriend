package kumbayah.withfriend.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class KakaoLoginController {
    @RequestMapping("/kakao/login")
    public String login(@RequestParam(value = "code") String code) {
        return null;
    }
}

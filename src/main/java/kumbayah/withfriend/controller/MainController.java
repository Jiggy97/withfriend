package kumbayah.withfriend.controller;

import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.dto.kakao.KakaoDTO;
import kumbayah.withfriend.service.kakao.KakaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {
    private final KakaoService kakaoService;

    MainController(KakaoService kakaoService) {
        this.kakaoService = kakaoService;
    }

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());
        return "index";
    }

    @GetMapping("/home")
    public String goToHome(Model model, HttpSession session) throws Exception {
        String accessToken = (String) session.getAttribute("access_token");
        KakaoDTO kakaoInfo = kakaoService.getUserInfoWithToken(accessToken);
        model.addAttribute("kakaoInfo", kakaoInfo);

        String agreeUrl = kakaoService.getFriendsInfo();
        model.addAttribute("agreeUrl", agreeUrl);
        return "home";
    }
}

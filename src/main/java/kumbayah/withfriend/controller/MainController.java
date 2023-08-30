package kumbayah.withfriend.controller;

import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.service.KakaoService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) throws Exception {
        String accessToken = (String) session.getAttribute("access_token");
        Long userId = (Long) session.getAttribute("user_id");
        session.removeAttribute("access_token");
        session.removeAttribute("user_id");
        kakaoService.logout(accessToken, userId);

        model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());
        return "index";
    }

    @GetMapping("/disconnect")
    public String disconnectKakao(HttpSession session, Model model) throws Exception {
        try {
            String accessToken = (String) session.getAttribute("access_token");
            kakaoService.disconnectKakaoAccount(accessToken);
            session.removeAttribute("access_token");
            ResponseEntity.ok("Kakao account disconnected");

            model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());
            return "index";
        } catch (Exception e) {
            throw new Exception("Fail disconnect");
        }
    }
}

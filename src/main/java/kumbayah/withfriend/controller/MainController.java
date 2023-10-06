package kumbayah.withfriend.controller;

import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.dto.kakao.KakaoDTO;
import kumbayah.withfriend.dto.user.UserDTO;
import kumbayah.withfriend.service.kakao.KakaoService;
import kumbayah.withfriend.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {
    private final KakaoService kakaoService;
    private final UserService userService;

    MainController(KakaoService kakaoService, UserService userService) {
        this.kakaoService = kakaoService;
        this.userService = userService;
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

        UserDTO userDTO = userService.findByUserId(kakaoInfo.getId());
        model.addAttribute("userDTO", userDTO);

        String agreeUrl = kakaoService.getFriendsInfo();
        model.addAttribute("agreeUrl", agreeUrl);
        return "home";
    }
}

package kumbayah.withfriend.controller.kakao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.dto.kakao.KakaoDTO;
import kumbayah.withfriend.dto.kakao.KakaoFriendsInfoDTO;
import kumbayah.withfriend.dto.user.UserDTO;
import kumbayah.withfriend.service.kakao.KakaoService;
import kumbayah.withfriend.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kakao")
public class KakaoController {

    private final KakaoService kakaoService;
    private final UserService userService;

    KakaoController(KakaoService kakaoService, UserService userService) {
        this.kakaoService = kakaoService;
        this.userService = userService;
    }

    @RequestMapping("/callback")
    public String home(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"), session);
        session.setAttribute("user_id", kakaoInfo.getId());

        userService.register(kakaoInfo);
        UserDTO userDTO = userService.findByUserId(kakaoInfo.getId());

        model.addAttribute("userDTO", userDTO);

        String agreeUrl = kakaoService.getFriendsInfo();
        model.addAttribute("agreeUrl", agreeUrl);
        return "home";
    }

    @GetMapping("/friendsInfo")
    public String getFriendsInfo(HttpSession session, Model model) throws Exception {
        String accessToken = (String) session.getAttribute("access_token");
        KakaoFriendsInfoDTO friendsInfo = kakaoService.getFriendsInfo(accessToken);

        model.addAttribute("friendsInfo", friendsInfo);

        return "friendsList";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) throws Exception {
        long userId = (long) session.getAttribute("user_id");
        session.removeAttribute("user_id");
        session.removeAttribute("access_token");
        kakaoService.logout(userId);

        model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());
        return "index";
    }

    @GetMapping("unlink")
    public String unlink(HttpSession session, Model model) {
        String accessToken = (String) session.getAttribute("access_token");
        session.removeAttribute("user_id");
        session.removeAttribute("access_token");
        kakaoService.unlink(accessToken);

        model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());
        return "index";
    }
}

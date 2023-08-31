package kumbayah.withfriend.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.dto.KakaoDTO;
import kumbayah.withfriend.dto.KakaoFriendsInfoDTO;
import kumbayah.withfriend.service.KakaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kakao")
public class KakaoController {

    private final KakaoService kakaoService;

    KakaoController(KakaoService kakaoService) {
        this.kakaoService = kakaoService;
    }

    @RequestMapping("/callback")
    public String home(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"), session);
        session.setAttribute("user_id", kakaoInfo.getId());
        model.addAttribute("kakaoInfo", kakaoInfo);
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

package kumbayah.withfriend.controller.kakao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.dto.kakao.KakaoDTO;
import kumbayah.withfriend.dto.kakao.KakaoFriendsInfoDTO;
import kumbayah.withfriend.dto.user.UserDTO;
import kumbayah.withfriend.service.kakao.KakaoService;
import kumbayah.withfriend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/kakao")
public class KakaoController {
    private final KakaoService kakaoService;
    private final UserService userService;

    @Autowired
    public KakaoController(KakaoService kakaoService, UserService userService) {
        this.kakaoService = kakaoService;
        this.userService = userService;
    }

    @RequestMapping("/callback")
    public String callback(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
        // 카카오 인증 코드로 사용자 정보 가져오기
        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"), session);

        // 사용자 등록
        userService.register(kakaoInfo);

        // 홈 페이지로 리다이렉션
        return "redirect:/main/home";
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

    @GetMapping("/unlink")
    public String unlink(HttpSession session, Model model) {
        String accessToken = (String) session.getAttribute("access_token");
        long userId = (Long) session.getAttribute("user_id");
        userService.unlink(userId);

        session.removeAttribute("user_id");
        session.removeAttribute("access_token");
        kakaoService.unlink(accessToken);

        model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());
        return "index";
    }
}

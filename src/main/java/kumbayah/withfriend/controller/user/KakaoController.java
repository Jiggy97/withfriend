package kumbayah.withfriend.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.dto.KakaoDTO;
import kumbayah.withfriend.service.KakaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kakao")
public class KakaoController {

    private final KakaoService kakaoService;

    KakaoController(KakaoService kakaoService) {
        this.kakaoService = kakaoService;
    }

//    @RequestMapping("/callback")
//    // HttpServletRequest 는 자바 Servlet 에서 제공하는 인터페이스로 웹 어플리케이션이 클라이언트와 HTTP 통신을 하는데 도움을 준다.
//    public ResponseEntity<MsgEntity> callback(HttpServletRequest request) throws Exception {
//        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"));
//
//        return ResponseEntity.ok().body(new MsgEntity("Success", kakaoInfo));
//    }

    @RequestMapping("/callback")
    public String home(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"), session);
        session.setAttribute("user_id", kakaoInfo.getId());
        model.addAttribute("kakaoInfo", kakaoInfo);
        return "home";
    }
}

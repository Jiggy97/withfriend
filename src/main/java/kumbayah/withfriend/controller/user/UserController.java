package kumbayah.withfriend.controller.user;

import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.dto.kakao.KakaoDTO;
import kumbayah.withfriend.dto.user.UserDTO;
import kumbayah.withfriend.dto.user.UserTradeRequestDataDTO;
import kumbayah.withfriend.entity.trustMarketplace.GoodsEntity;
import kumbayah.withfriend.service.kakao.KakaoService;
import kumbayah.withfriend.service.trustMarketplace.TrustMarketplaceService;
import kumbayah.withfriend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final KakaoService kakaoService;
    private final TrustMarketplaceService trustMarketplaceService;

    @Autowired
    public UserController(UserService userService, KakaoService kakaoService, TrustMarketplaceService trustMarketplaceService) {
        this.userService = userService;
        this.kakaoService = kakaoService;
        this.trustMarketplaceService = trustMarketplaceService;
    }

    @PostMapping("/point")
    public String trade(@RequestBody UserTradeRequestDataDTO userTradeRequestDataDTO, HttpSession session, Model model) throws Exception {
        userService.Trade(userTradeRequestDataDTO);

        // 거래 완료 시 클라이언트에 변경 내용 반영
        String accessToken = (String) session.getAttribute("access_token");
        KakaoDTO kakaoInfo = kakaoService.getUserInfoWithToken(accessToken);
        UserDTO userDTO = userService.findByUserId(kakaoInfo.getId());
        List<GoodsEntity> friendsGoodsList = trustMarketplaceService.findFriendsGoods(accessToken);
        model.addAttribute("friendsGoodsList", friendsGoodsList);
        model.addAttribute("userDTO", userDTO);

        return "friendsGoods";
    }
}

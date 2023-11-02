package kumbayah.withfriend.controller.trustMarketplace;

import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.dto.kakao.KakaoDTO;
import kumbayah.withfriend.dto.user.UserDTO;
import kumbayah.withfriend.entity.trustMarketplace.GoodsEntity;
import kumbayah.withfriend.service.kakao.KakaoService;
import kumbayah.withfriend.service.trustMarketplace.TrustMarketplaceService;

import kumbayah.withfriend.dto.trustMarketplace.GoodsDTO;
import kumbayah.withfriend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("trust")
public class TrustMarketplaceController {
    private final TrustMarketplaceService trustMarketplaceService;
    private final KakaoService kakaoService;
    private final UserService userService;

    @Autowired
    public TrustMarketplaceController(TrustMarketplaceService trustMarketplaceService, KakaoService kakaoService, UserService userService) {
        this.trustMarketplaceService = trustMarketplaceService;
        this.kakaoService = kakaoService;
        this.userService = userService;
    }

    @GetMapping("/goods")
    public String postForm() {
        return "postForm";
    }

    @GetMapping("/")
    public String trustMarket() {
        return "trustMarketplace";
    }

    @GetMapping("/allGoods")
    public String findAll(Model model) {
        List<GoodsDTO> goodsList = trustMarketplaceService.findAll();
        model.addAttribute("goodsList", goodsList);

        return "allGoods";
    }

    @GetMapping("/friendsGoods")
    public String findFriendsGoods(HttpSession session, Model model) throws Exception {
        String accessToken = (String) session.getAttribute("access_token");
        KakaoDTO kakaoInfo = kakaoService.getUserInfoWithToken(accessToken);
        UserDTO userDTO = userService.findByUserId(kakaoInfo.getId());
        List<GoodsEntity> friendsGoodsList = trustMarketplaceService.findFriendsGoods(accessToken);
        model.addAttribute("friendsGoodsList", friendsGoodsList);
        model.addAttribute("userDTO", userDTO);

        return "friendsGoods";
    }

    @GetMapping("/myGoods")
    public String findMyGoods(HttpSession session, Model model) {
        long userId = (Long) session.getAttribute("user_id" );
        List<GoodsDTO> myGoodsList = trustMarketplaceService.findMyGoods(userId);
        model.addAttribute("myGoodsList", myGoodsList);

        return "myGoodsList";
    }

    @PostMapping("/goods")
    // 폼 데이터를 받을 때는 RequestBody가 아닌 ModelAttribute를 통해 데이터를 받아야 한다.
    public String postGoods(@ModelAttribute GoodsDTO goods, HttpSession session, Model model) throws Exception {
        String accessToken = (String) session.getAttribute("access_token");
        KakaoDTO kakaoDTO = kakaoService.getUserInfoWithToken(accessToken);
        long userId = kakaoDTO.getId();
        String seller = kakaoDTO.getNickname();
        trustMarketplaceService.postGoods(goods, seller, userId);

        KakaoDTO kakaoInfo = kakaoService.getUserInfoWithToken(accessToken);
        UserDTO userDTO = userService.findByUserId(kakaoInfo.getId());
        model.addAttribute("userDTO", userDTO);

        String agreeUrl = kakaoService.getFriendsInfo();
        model.addAttribute("agreeUrl", agreeUrl);

        return "home";
    }

    @GetMapping("/goods/{id}")
    public String detailAndUpdate(@PathVariable Long id, Model model) {
        GoodsDTO goods = trustMarketplaceService.findById(id);
        model.addAttribute("goods", goods);

        return "updateForm";
    }

    @PostMapping("/goods/")
    public String updateGoods(@ModelAttribute GoodsDTO goodsDTO, HttpSession session, Model model) throws Exception {
        String accessToken = (String) session.getAttribute("access_token");
        KakaoDTO kakaoDTO = kakaoService.getUserInfoWithToken(accessToken);
        long userId = kakaoDTO.getId();
        String seller = kakaoDTO.getNickname();
        trustMarketplaceService.update(goodsDTO, seller, userId);

        List<GoodsDTO> myGoodsList = trustMarketplaceService.findMyGoods(userId);
        model.addAttribute("myGoodsList", myGoodsList);

        return "myGoodsList";
    }
}

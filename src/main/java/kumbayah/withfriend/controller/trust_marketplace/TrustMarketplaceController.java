package kumbayah.withfriend.controller.trust_marketplace;

import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.dto.kakao.KakaoDTO;
import kumbayah.withfriend.service.kakao.KakaoService;
import kumbayah.withfriend.service.trust_marketplace.TrustMarketplaceService;

import kumbayah.withfriend.dto.trust_marketplace.GoodsDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("trust")
public class TrustMarketplaceController {
    private final TrustMarketplaceService trustMarketplaceService;
    private final KakaoService kakaoService;
    public TrustMarketplaceController(TrustMarketplaceService trustMarketplaceService, KakaoService kakaoService) {
        this.trustMarketplaceService = trustMarketplaceService;
        this.kakaoService = kakaoService;
    }

    @GetMapping("/goods")
    public String postForm() {
        return "post-form";
    }

    @GetMapping("/")
    public String trustMarket() {
        return "trust_marketplace";
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
        String buyer = kakaoService.getUserInfoWithToken(accessToken).getNickname();
        List<GoodsDTO> friendsGoodsList = trustMarketplaceService.findFriendsGoods(accessToken);
        model.addAttribute("friendsGoodsList", friendsGoodsList);
        model.addAttribute("buyer", buyer);

        return "friendsGoods";
    }

    @GetMapping("/myGoods")
    public String findMyGoods(HttpSession session, Model model) throws Exception {
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
        List<GoodsDTO> goodsList = trustMarketplaceService.findAll();
        model.addAttribute("goodsList", goodsList);

        return "trust_marketplace";
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

        return "trust_marketplace";
    }
}

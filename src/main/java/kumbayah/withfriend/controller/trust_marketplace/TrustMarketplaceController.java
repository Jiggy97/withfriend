package kumbayah.withfriend.controller.trust_marketplace;

import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.dto.kakao.KakaoDTO;
import kumbayah.withfriend.dto.kakao.trust_marketplace.GoodsListDTO;
import kumbayah.withfriend.service.kakao.KakaoService;
import kumbayah.withfriend.service.trust_marketplace.TrustMarketplaceService;

import kumbayah.withfriend.dto.kakao.trust_marketplace.GoodsDTO;
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
    public String trustMarket(Model model) {
        List<GoodsListDTO> goodsList = trustMarketplaceService.findAll();
        model.addAttribute("goodsList", goodsList);

        return "trust_marketplace";
    }

    @PostMapping("/goods")
    // 폼 데이터를 받을 때는 RequestBody가 아닌 ModelAttribute를 통해 데이터를 받아야 한다.
    public String postGoods(@ModelAttribute GoodsDTO goods, HttpSession session, Model model) throws Exception {
        String accessToken = (String) session.getAttribute("access_token");
        KakaoDTO kakaoDTO = kakaoService.getUserInfoWithToken(accessToken);
        String nickname = kakaoDTO.getNickname();
        trustMarketplaceService.postGoods(goods, nickname);
        List<GoodsListDTO> goodsList = trustMarketplaceService.findAll();
        model.addAttribute("goodsList", goodsList);

        return "trust_marketplace";
    }
}

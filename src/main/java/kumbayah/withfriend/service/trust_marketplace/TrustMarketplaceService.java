package kumbayah.withfriend.service.trust_marketplace;

import com.fasterxml.jackson.core.JsonProcessingException;
import kumbayah.withfriend.dto.trust_marketplace.GoodsDTO;
import kumbayah.withfriend.dto.trust_marketplace.GoodsListDTO;
import kumbayah.withfriend.entity.trust_marketplace.GoodsEntity;
import kumbayah.withfriend.repository.trust_marketplace.TrustMarketplaceRepository;
import kumbayah.withfriend.service.kakao.KakaoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrustMarketplaceService {
    private final TrustMarketplaceRepository trustMarketplaceRepository;
    private final KakaoService kakaoService;

    public TrustMarketplaceService(TrustMarketplaceRepository trustMarketplaceRepository, KakaoService kakaoService) {
        this.trustMarketplaceRepository = trustMarketplaceRepository;
        this.kakaoService = kakaoService;
    }

    public List<GoodsListDTO> findAll() {
        List<GoodsEntity> goodsEntityList = trustMarketplaceRepository.findAll();
        List<GoodsListDTO> goodsList = new ArrayList<>();
        for (GoodsEntity goodsEntity: goodsEntityList) {
            GoodsListDTO goods = GoodsListDTO.toGoodsListDTO(goodsEntity);
            goodsList.add(goods);
        }

        return goodsList;
    }

    public List<GoodsListDTO> findGoodsOfFriend(String accessToken) throws JsonProcessingException {
        List<Long> friendIdList = kakaoService.getFriendIdList(accessToken);
        List<GoodsEntity> goodsEntityList = trustMarketplaceRepository.findAll();
        List<GoodsListDTO> friendGoodsList = new ArrayList<>();
        for (GoodsEntity goodsEntity : goodsEntityList) {
            if (friendIdList.contains(goodsEntity.getUserId())) {
                // Entity > DTO 변환 제대로 이루어 졌는지
                GoodsListDTO friendGoodsDTO = GoodsListDTO.toGoodsListDTO(goodsEntity);
                friendGoodsList.add(friendGoodsDTO);
            }
        }

        return friendGoodsList;
    }

    public void postGoods(GoodsDTO goods, String nickname, long userId) {
        GoodsEntity goodsEntity = GoodsEntity.toSaveEntity(goods, nickname, userId);
        trustMarketplaceRepository.save(goodsEntity);
    }
}

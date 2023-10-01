package kumbayah.withfriend.service.trust_marketplace;

import com.fasterxml.jackson.core.JsonProcessingException;
import kumbayah.withfriend.dto.trustMarketplace.GoodsDTO;
import kumbayah.withfriend.entity.user.GoodsEntity;
import kumbayah.withfriend.repository.trustMarketplace.TrustMarketplaceRepository;
import kumbayah.withfriend.service.kakao.KakaoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrustMarketplaceService {
    private final TrustMarketplaceRepository trustMarketplaceRepository;
    private final KakaoService kakaoService;

    public TrustMarketplaceService(TrustMarketplaceRepository trustMarketplaceRepository, KakaoService kakaoService) {
        this.trustMarketplaceRepository = trustMarketplaceRepository;
        this.kakaoService = kakaoService;
    }

    public List<GoodsDTO> findAll() {
        List<GoodsEntity> goodsEntityList = trustMarketplaceRepository.findAll();
        List<GoodsDTO> goodsList = new ArrayList<>();
        for (GoodsEntity goodsEntity: goodsEntityList) {
            GoodsDTO goods = GoodsDTO.toGoodsDTO(goodsEntity);
            goodsList.add(goods);
        }

        return goodsList;
    }

    public List<GoodsDTO> findFriendsGoods(String accessToken) throws JsonProcessingException {
        List<Long> friendIdList = kakaoService.getFriendIdList(accessToken);
        List<GoodsEntity> goodsEntityList = trustMarketplaceRepository.findAll();
        List<GoodsDTO> friendGoodsList = new ArrayList<>();
        for (GoodsEntity goodsEntity : goodsEntityList) {
            if (friendIdList.contains(goodsEntity.getUserId())) {
                // Entity > DTO 변환 제대로 이루어 졌는지
                GoodsDTO friendGoodsDTO = GoodsDTO.toGoodsDTO(goodsEntity);
                friendGoodsList.add(friendGoodsDTO);
            }
        }

        return friendGoodsList;
    }

    public List<GoodsDTO> findMyGoods(long userId) throws JsonProcessingException {
        List<GoodsEntity> goodsEntityList = trustMarketplaceRepository.findAllByUserId(userId);
        List<GoodsDTO> myGoodsList = new ArrayList<>();
        for (GoodsEntity goodsEntity : goodsEntityList) {
            GoodsDTO myGoodsDTO = GoodsDTO.toGoodsDTO(goodsEntity);
            myGoodsList.add(myGoodsDTO);
        }

        return myGoodsList;
    }

    public void postGoods(GoodsDTO goods,String seller, long userId) {
        GoodsEntity goodsEntity = GoodsEntity.toSaveEntity(goods, seller, userId);
        trustMarketplaceRepository.save(goodsEntity);
    }


    public GoodsDTO findById(Long id) {
        Optional<GoodsEntity> optionalGoodsEntity = trustMarketplaceRepository.findById(id);
        if (optionalGoodsEntity.isPresent()) {
            GoodsEntity goodsEntity = optionalGoodsEntity.get();
            return GoodsDTO.toGoodsDTO(goodsEntity);
        } else {
            return null;
        }
    }

    public void update(GoodsDTO goodsDTO, String seller, long userId) {
        GoodsEntity goodsEntity = GoodsEntity.toUpdateEntity(goodsDTO, seller, userId);
        trustMarketplaceRepository.save(goodsEntity);
    }
}

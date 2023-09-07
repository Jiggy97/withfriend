package kumbayah.withfriend.service.trust_marketplace;

import kumbayah.withfriend.dto.trust_marketplace.GoodsDTO;
import kumbayah.withfriend.dto.trust_marketplace.GoodsListDTO;
import kumbayah.withfriend.entity.trust_marketplace.GoodsEntity;
import kumbayah.withfriend.repository.trust_marketplace.TrustMarketplaceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrustMarketplaceService {
    private final TrustMarketplaceRepository trustMarketplaceRepository;

    public TrustMarketplaceService(TrustMarketplaceRepository trustMarketplaceRepository) {
        this.trustMarketplaceRepository = trustMarketplaceRepository;
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

    public List<GoodsListDTO> findGoodsOfFriend() {
        List<GoodsEntity> goodsEntityList = trustMarketplaceRepository.findAllById()
    }

    public void postGoods(GoodsDTO goods, String nickname, long userId) {
        GoodsEntity goodsEntity = GoodsEntity.toSaveEntity(goods, nickname, userId);
        trustMarketplaceRepository.save(goodsEntity);
    }
}

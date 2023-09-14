package kumbayah.withfriend.repository.trust_marketplace;

import kumbayah.withfriend.entity.trust_marketplace.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrustMarketplaceRepository extends JpaRepository<GoodsEntity, Long> {
    List<GoodsEntity> findAllByUserId(Long userId);
}

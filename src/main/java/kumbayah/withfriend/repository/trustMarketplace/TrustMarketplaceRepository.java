package kumbayah.withfriend.repository.trustMarketplace;

import kumbayah.withfriend.entity.trustMarketplace.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrustMarketplaceRepository extends JpaRepository<GoodsEntity, Long> {
    List<GoodsEntity> findAllByUserId(Long userId);
}

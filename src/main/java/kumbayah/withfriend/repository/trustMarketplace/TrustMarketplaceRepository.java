package kumbayah.withfriend.repository.trustMarketplace;

import kumbayah.withfriend.entity.user.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrustMarketplaceRepository extends JpaRepository<GoodsEntity, Long> {
    List<GoodsEntity> findAllByUserId(Long userId);
}

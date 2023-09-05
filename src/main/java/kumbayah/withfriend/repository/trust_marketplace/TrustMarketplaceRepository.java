package kumbayah.withfriend.repository.trust_marketplace;

import kumbayah.withfriend.entity.trust_marketplace.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrustMarketplaceRepository extends JpaRepository<GoodsEntity, String> {

}

package kumbayah.withfriend.repository.trade;


import kumbayah.withfriend.entity.trade.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<TradeEntity, Long> {
}

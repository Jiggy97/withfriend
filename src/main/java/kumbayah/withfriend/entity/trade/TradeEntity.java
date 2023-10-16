package kumbayah.withfriend.entity.trade;

import jakarta.persistence.*;
import kumbayah.withfriend.dto.user.TradeDTO;

@Entity
@Table(name = "trade_history_table")
public class TradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String makeMerchantUid;

    @Column
    private long sellerUserId;

    @Column
    private long buyerUserId;

    @Column
    private double tradedPoint;

    @Column
    private int tradedQuan;

    @Column
    private long goodsId;

    public TradeEntity() {}
    public long getId() { return id; }
    public String getMakeMerchantUid() { return makeMerchantUid; }
    public long getSellerUserId() { return sellerUserId; }
    public long getBuyerUserId() { return buyerUserId; }
    public double getTradedPoint() { return tradedPoint; }
    public int getTradedQuan() { return tradedQuan; }
    public long getGoodsId() { return goodsId; }

    public void setId(long id) { this.id = id; }
    public void setMakeMerchantUid(String makeMerchantUid) { this.makeMerchantUid = makeMerchantUid; }
    public void setSellerUserId(long sellerUserId) { this.sellerUserId = sellerUserId; }
    public void setBuyerUserId(long buyerUserId) { this.buyerUserId = buyerUserId; }
    public void setTradedPoint(double tradedPoint) { this.tradedPoint = tradedPoint; }
    public void setTradedQuan(int tradedQuan) { this.tradedQuan = tradedQuan; }
    public void setGoodsId(long goodsId) { this.goodsId = goodsId; }

    public static TradeEntity toSaveEntity(TradeDTO tradeDTO) {
        TradeEntity tradeEntity = new TradeEntity();
        tradeEntity.setMakeMerchantUid(tradeDTO.getMakeMerchantUid());
        tradeEntity.setSellerUserId(tradeDTO.getSellerUserId());
        tradeEntity.setBuyerUserId(tradeDTO.getBuyerUserId());
        tradeEntity.setTradedPoint(tradeDTO.getTradedPoint());
        tradeEntity.setTradedQuan(tradeDTO.getTradedQuan());
        tradeEntity.setGoodsId(tradeDTO.getGoodsId());

        return tradeEntity;
    }
}

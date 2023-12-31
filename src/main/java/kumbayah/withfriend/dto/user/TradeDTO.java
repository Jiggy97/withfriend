package kumbayah.withfriend.dto.user;

public class TradeDTO {
    private long id;
    private String makeMerchantUid;
    private long sellerUserId;
    private long buyerUserId;
    private double tradedPoint;
    private int tradedQuan;
    private long goodsId;

    public long getId() { return id; }
    public String getMakeMerchantUid() { return makeMerchantUid; }
    public long getSellerUserId() { return sellerUserId; }
    public long getBuyerUserId() { return buyerUserId; }
    public double getTradedPoint() { return tradedPoint; }
    public int getTradedQuan() { return tradedQuan; }
    public long getGoodsId() { return goodsId; }

    public void setId(long id) { this.id = id; }
    public void setMakeMerchantUid(String makeMerchantUid) { this.makeMerchantUid = makeMerchantUid; }
    public void setBuyerUserId(long buyerUserId) { this.buyerUserId = buyerUserId; }
    public void setSellerUserId(long sellerUserId) { this.sellerUserId = sellerUserId; }
    public void setTradedPoint(double tradedPoint) { this.tradedPoint = tradedPoint; }
    public void setTradedQuan(int tradedQuan) { this.tradedQuan = tradedQuan; }
    public void setGoodsId(long goodsId) { this.goodsId = goodsId; }


    public static TradeDTO toTradeDTO(UserTradeRequestDataDTO userTradeRequestDataDTO) {
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setMakeMerchantUid(userTradeRequestDataDTO.getMakeMerchantUid());
        tradeDTO.setSellerUserId(userTradeRequestDataDTO.getSellerUserId());
        tradeDTO.setBuyerUserId(userTradeRequestDataDTO.getBuyerUserId());
        tradeDTO.setTradedPoint(userTradeRequestDataDTO.getGoodsPrice());
        tradeDTO.setTradedQuan(userTradeRequestDataDTO.getPurchaseQuan());
        tradeDTO.setGoodsId(userTradeRequestDataDTO.getGoodsId());

        return tradeDTO;
    }
}

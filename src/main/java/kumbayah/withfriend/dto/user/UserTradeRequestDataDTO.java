package kumbayah.withfriend.dto.user;

public class UserTradeRequestDataDTO {
    private long goodsId;
    private String goodsName;
    private double goodsPrice;
    private int goodsStock;
    private long buyerId;
    private String makeMerchantUid;

    public UserTradeRequestDataDTO() {}

    public long getGoodsId() { return goodsId; }
    public String getGoodsName() { return goodsName; }
    public double getGoodsPrice() { return goodsPrice; }
    public int getGoodsStock() { return goodsStock; }
    public long getBuyerId() { return buyerId; }
    public String getMakeMerchantUid() { return makeMerchantUid; }
}

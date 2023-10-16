package kumbayah.withfriend.dto.user;

public class UserTradeRequestDataDTO {
    private long goodsId;
    private String goodsName;
    private double goodsPrice;
    private int goodsStock;
    private int purchaseQuan;
    private long sellerUserId;
    private long buyerUserId;
    private String makeMerchantUid;

    public UserTradeRequestDataDTO() {}

    public long getGoodsId() { return goodsId; }
    public String getGoodsName() { return goodsName; }
    public double getGoodsPrice() { return goodsPrice; }
    public int getGoodsStock() { return goodsStock; }
    public int getPurchaseQuan() { return purchaseQuan; }
    public long getSellerUserId() { return sellerUserId; }
    public long getBuyerUserId() { return buyerUserId; }
    public String getMakeMerchantUid() { return makeMerchantUid; }
}

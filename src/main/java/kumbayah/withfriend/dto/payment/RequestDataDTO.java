package kumbayah.withfriend.dto.payment;

public class RequestDataDTO {
    private String impUid;
    private String merchantUid;
    private double chargePoint;
    private long userId;

    public RequestDataDTO() {}

    public String getImpUid() { return impUid; }
    public String getMerchantUid() { return merchantUid; }
    public double getChargePoint() { return chargePoint; }
    public long getUserId() { return userId; }

    public void setImpUid(String impUid) { this.impUid = impUid; }
    public void setMerchantUid(String merchantUid) { this.merchantUid = merchantUid; }
    public void setChargePoint(double chargePoint) { this.chargePoint = chargePoint; }
    public void setUserId(long userId) { this.userId = userId; }
}

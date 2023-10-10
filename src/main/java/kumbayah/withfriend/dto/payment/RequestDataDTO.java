package kumbayah.withfriend.dto.payment;

public class RequestDataDTO {
    private long impUid;
    private long merchantUid;
    private double chargePoint;
    private long userId;

    public RequestDataDTO() {}

    public long getImpUid() { return impUid; }
    public long getMerchantUid() { return merchantUid; }
    public double getChargePoint() { return chargePoint; }
    public long getUserId() { return userId; }

    public void setImpUid(long impUid) { this.impUid = impUid; }
    public void setMerchantUid(long merchantUid) { this.merchantUid = merchantUid; }
    public void setChargePoint(double chargePoint) { this.chargePoint = chargePoint; }
    public void setUserId(long userId) { this.userId = userId; }
}

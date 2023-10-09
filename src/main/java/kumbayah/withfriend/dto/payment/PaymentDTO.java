package kumbayah.withfriend.dto.payment;

public class PaymentDTO {
    private long impUid;
    private long merchantUid;
    private double chargePoint;

    public PaymentDTO() {}

    public long getImpUid() { return impUid; }
    public long getMerchantUid() { return merchantUid; }
    public double getChargePoint() { return chargePoint; }

    public void setImpUid(long impUid) { this.impUid = impUid; }
    public void setMerchantUid(long merchantUid) { this.merchantUid = merchantUid; }
    public void setChargePoint(long chargePoint) { this.chargePoint = chargePoint; }
}

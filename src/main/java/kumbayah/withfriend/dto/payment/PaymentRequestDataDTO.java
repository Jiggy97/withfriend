package kumbayah.withfriend.dto.payment;

public class PaymentRequestDataDTO {
    private String impUid;
    private String merchantUid;
    private double chargePoint;
    private long userId;

    public PaymentRequestDataDTO() {}

    public String getImpUid() { return impUid; }
    public String getMerchantUid() { return merchantUid; }
    public double getChargePoint() { return chargePoint; }
    public long getUserId() { return userId; }
}

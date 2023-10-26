package kumbayah.withfriend.dto.payment;

public class PaymentRequestDataDTO {
    private String impUid;
    private double chargePoint;
    private long userId;

    public String getImpUid() { return impUid; }
    public double getChargePoint() { return chargePoint; }
    public long getUserId() { return userId; }
}

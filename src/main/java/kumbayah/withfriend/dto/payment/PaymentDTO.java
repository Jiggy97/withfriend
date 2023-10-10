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
    public void setChargePoint(double chargePoint) { this.chargePoint = chargePoint; }

    public static PaymentDTO toPaymentDTO(RequestDataDTO requestDataDTO) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setImpUid(requestDataDTO.getImpUid());
        paymentDTO.setMerchantUid(requestDataDTO.getMerchantUid());
        paymentDTO.setChargePoint(requestDataDTO.getChargePoint());

        return paymentDTO;
    }
}
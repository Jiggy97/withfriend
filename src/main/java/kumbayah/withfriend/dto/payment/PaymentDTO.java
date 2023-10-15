package kumbayah.withfriend.dto.payment;

public class PaymentDTO {
    private String impUid;
    private String merchantUid;
    private double chargePoint;

    public PaymentDTO() {}

    public String getImpUid() { return impUid; }
    public String getMerchantUid() { return merchantUid; }
    public double getChargePoint() { return chargePoint; }

    public void setImpUid(String impUid) { this.impUid = impUid; }
    public void setMerchantUid(String merchantUid) { this.merchantUid = merchantUid; }
    public void setChargePoint(double chargePoint) { this.chargePoint = chargePoint; }

    public static PaymentDTO toPaymentDTO(PaymentRequestDataDTO paymentRequestDataDTO) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setImpUid(paymentRequestDataDTO.getImpUid());
        paymentDTO.setMerchantUid(paymentRequestDataDTO.getMerchantUid());
        paymentDTO.setChargePoint(paymentRequestDataDTO.getChargePoint());

        return paymentDTO;
    }
}

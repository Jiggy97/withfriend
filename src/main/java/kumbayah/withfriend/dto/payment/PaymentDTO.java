package kumbayah.withfriend.dto.payment;

public class PaymentDTO {
    private String impUid;
    private double chargePoint;

    public PaymentDTO() {}

    public String getImpUid() { return impUid; }
    public double getChargePoint() { return chargePoint; }

    public void setImpUid(String impUid) { this.impUid = impUid; }
    public void setChargePoint(double chargePoint) { this.chargePoint = chargePoint; }

    public static PaymentDTO toPaymentDTO(PaymentRequestDataDTO paymentRequestDataDTO) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setImpUid(paymentRequestDataDTO.getImpUid());
        paymentDTO.setChargePoint(paymentRequestDataDTO.getChargePoint());

        return paymentDTO;
    }
}

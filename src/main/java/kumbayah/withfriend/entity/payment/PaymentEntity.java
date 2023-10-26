package kumbayah.withfriend.entity.payment;

import jakarta.persistence.*;
import kumbayah.withfriend.dto.payment.PaymentDTO;

@Entity
@Table(name = "payment_table")
public class PaymentEntity {
    @Id
    private String impUid;

    @Column
    private double chargePoint;

    public String getImpUid() { return impUid; }
    public double getChargePoint() { return chargePoint; }

    public void setImpUid(String impUid) { this.impUid = impUid; }
    public void setChargePoint(double chargePoint) { this.chargePoint = chargePoint; }

    public static PaymentEntity toSaveEntity(PaymentDTO paymentDTO) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setImpUid(paymentDTO.getImpUid());
        paymentEntity.setChargePoint(paymentDTO.getChargePoint());

        return paymentEntity;
    }
}

package kumbayah.withfriend.entity.payment;

import jakarta.persistence.*;
import kumbayah.withfriend.dto.payment.PaymentDTO;

@Entity
@Table(name = "payment_table")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long impUid;

    @Column
    private long merchantUid;

    @Column
    private double chargePoint;

    public PaymentEntity() {}

    public long getId() { return id; }
    public long getImpUid() { return impUid; }
    public long getMerchantUid() { return merchantUid; }
    public double getChargePoint() { return chargePoint; }

    public void setId(long id) { this.id = id; }
    public void setImpUid(long impUid) { this.impUid = impUid; }
    public void setMerchantUid(long merchantUid) { this.merchantUid = merchantUid; }
    public void setChargePoint(double chargePoint) { this.chargePoint = chargePoint; }

    public static PaymentEntity toSaveEntity(PaymentDTO paymentDTO) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setImpUid(paymentDTO.getImpUid());
        paymentEntity.setMerchantUid(paymentDTO.getMerchantUid());
        paymentEntity.setChargePoint(paymentDTO.getChargePoint());

        return paymentEntity;
    }
}

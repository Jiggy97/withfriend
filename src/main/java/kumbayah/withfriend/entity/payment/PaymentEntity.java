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
    private String impUid;

    @Column
    private String merchantUid;

    @Column
    private double chargePoint;

    public PaymentEntity() {}

    public long getId() { return id; }
    public String getImpUid() { return impUid; }
    public String getMerchantUid() { return merchantUid; }
    public double getChargePoint() { return chargePoint; }

    public void setId(long id) { this.id = id; }
    public void setImpUid(String impUid) { this.impUid = impUid; }
    public void setMerchantUid(String merchantUid) { this.merchantUid = merchantUid; }
    public void setChargePoint(double chargePoint) { this.chargePoint = chargePoint; }

    public static PaymentEntity toSaveEntity(PaymentDTO paymentDTO) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setImpUid(paymentDTO.getImpUid());
        paymentEntity.setMerchantUid(paymentDTO.getMerchantUid());
        paymentEntity.setChargePoint(paymentDTO.getChargePoint());

        return paymentEntity;
    }
}

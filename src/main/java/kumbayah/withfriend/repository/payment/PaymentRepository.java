package kumbayah.withfriend.repository.payment;

import kumbayah.withfriend.entity.payment.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}

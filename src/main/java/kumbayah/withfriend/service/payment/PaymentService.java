package kumbayah.withfriend.service.payment;

import kumbayah.withfriend.dto.payment.PaymentDTO;
import kumbayah.withfriend.entity.payment.PaymentEntity;
import kumbayah.withfriend.repository.payment.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    public void save(PaymentDTO paymentDTO) {
        PaymentEntity paymentEntity = PaymentEntity.toSaveEntity(paymentDTO);
        paymentRepository.save(paymentEntity);
    }
}

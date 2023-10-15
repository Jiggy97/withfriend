package kumbayah.withfriend.controller.payment;

import kumbayah.withfriend.dto.payment.PaymentDTO;
import kumbayah.withfriend.dto.payment.PaymentRequestDataDTO;
import kumbayah.withfriend.service.payment.PaymentService;
import kumbayah.withfriend.service.user.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final UserService userService;

    PaymentController(PaymentService paymentService, UserService userService) {
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @PostMapping("/point")
    public void chargePage(@RequestBody PaymentRequestDataDTO paymentRequestDataDTO) {
        PaymentDTO paymentDTO = PaymentDTO.toPaymentDTO(paymentRequestDataDTO);
        paymentService.save(paymentDTO);
        userService.chargePoint(paymentRequestDataDTO.getUserId(), paymentRequestDataDTO.getChargePoint());
    }
}

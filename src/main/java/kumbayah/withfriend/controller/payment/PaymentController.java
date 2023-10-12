package kumbayah.withfriend.controller.payment;

import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.controller.MainController;
import kumbayah.withfriend.dto.payment.PaymentDTO;
import kumbayah.withfriend.dto.payment.RequestDataDTO;
import kumbayah.withfriend.service.payment.PaymentService;
import kumbayah.withfriend.service.user.UserService;
import org.springframework.ui.Model;
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
    public void chargePage(@RequestBody RequestDataDTO requestDataDTO) {
        PaymentDTO paymentDTO = PaymentDTO.toPaymentDTO(requestDataDTO);
        paymentService.save(paymentDTO);
        userService.chargePoint(requestDataDTO.getUserId(), requestDataDTO.getChargePoint());
    }
}

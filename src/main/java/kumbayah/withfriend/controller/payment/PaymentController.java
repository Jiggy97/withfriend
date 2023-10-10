package kumbayah.withfriend.controller.payment;

import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.controller.MainController;
import kumbayah.withfriend.dto.payment.PaymentDTO;
import kumbayah.withfriend.dto.payment.RequestDataDTO;
import kumbayah.withfriend.service.payment.PaymentService;
import kumbayah.withfriend.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final UserService userService;
    private final MainController mainController;

    PaymentController(PaymentService paymentService, UserService userService, MainController mainController) {
        this.paymentService = paymentService;
        this.userService = userService;
        this.mainController = mainController;
    }

    @PostMapping("/point")
    public void chargePage(@RequestBody RequestDataDTO requestDataDTO, Model model, HttpSession session) throws Exception {
        PaymentDTO paymentDTO = PaymentDTO.toPaymentDTO(requestDataDTO);
        paymentService.save(paymentDTO);
        userService.chargePoint(requestDataDTO.getUserId(), requestDataDTO.getChargePoint());

        mainController.goToHome(model, session);
    }
}

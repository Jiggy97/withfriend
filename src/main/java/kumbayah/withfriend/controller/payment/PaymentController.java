package kumbayah.withfriend.controller.payment;

import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.controller.MainController;
import kumbayah.withfriend.dto.payment.PaymentDTO;
import kumbayah.withfriend.service.payment.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final MainController mainController;

    public PaymentController(PaymentService paymentService, MainController mainController) {
        this.paymentService = paymentService;
        this.mainController = mainController;
    }

    @GetMapping("/point/{chargePoint}")
    public void chargePage(@RequestBody PaymentDTO paymentDTO, Model model, HttpSession session) throws Exception {
        paymentService.save(paymentDTO);


        mainController.goToHome(model, session);
    }
}

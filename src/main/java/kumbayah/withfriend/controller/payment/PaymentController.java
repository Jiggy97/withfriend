package kumbayah.withfriend.controller.payment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("payment")
public class PaymentController {


    @GetMapping("/point/{chargePoint}")
    public String chargePage(@PathVariable double chargePoint, Model model) {
        model.addAttribute("chargePoint", chargePoint);

        return "chargePoint";
    }
}

package kumbayah.withfriend.controller.portone;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import kumbayah.withfriend.service.portone.PortOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portone")
public class PortOneController {
    private final PortOneService portOneService;

    @Autowired
    public PortOneController(PortOneService portOneService) {
        this.portOneService = portOneService;
    }

    @GetMapping("/token")
    public String getToken(
            @RequestParam("imp_uid") String impUid,
            @RequestParam("charge_point") double chargePoint,
            HttpSession session) throws JsonProcessingException {
        portOneService.getToken(impUid, chargePoint, session);
        return impUid;
    }
}

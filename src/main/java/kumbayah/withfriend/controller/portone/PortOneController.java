package kumbayah.withfriend.controller.portone;

import kumbayah.withfriend.service.portone.PortOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/portone")
public class PortOneController {
    @Autowired
    private PortOneService portOneService;

    @GetMapping("/token")
    public ResponseEntity<String> getToken() {
        return portOneService.getToken();
    }
}

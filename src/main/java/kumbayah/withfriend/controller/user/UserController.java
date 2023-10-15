package kumbayah.withfriend.controller.user;

import kumbayah.withfriend.dto.user.UserTradeRequestDataDTO;
import kumbayah.withfriend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/point")
    public void trade(@RequestBody UserTradeRequestDataDTO userTradeRequestDataDTO) {
        userService.Trade(userTradeRequestDataDTO);

        // 거래 완료 시 클라이언트에 변경 내용 반영
    }
}

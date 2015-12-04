package app.controller.http;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by s-wada on 2015/11/27.
 */

@Controller
@RequestMapping("/websocket")
public class WebSocketController {

    @RequestMapping("/sample")
    public String index() {
        return "wssample";
    }

    @RequestMapping({"/", ""})
    public String chatRoom() {
        return "chatroom";
    }
}

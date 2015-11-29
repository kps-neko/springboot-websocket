package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by s-wada on 2015/11/27.
 */

@Controller
public class HomeController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping("/home")
    public String index() {
        return "home";
    }
//
    @MessageMapping("/hello")
//    @SendToUser("/queue/greetings")
//    @SendTo("/queue/greetings") // こちらは全配信。
    public void greeting(HelloMessage message) throws Exception {
        System.out.println("aaaaa");
        Thread.sleep(5000);
//        return new Greeting("Hello, " + message.getName());
        Greeting g = new Greeting("Hello, " + message.getName());
        simpMessagingTemplate.convertAndSendToUser("/user", "/queue/greetings", g);
    }

    @RequestMapping("/")
    public String chatRoomhtml() {
        return "chatroom";
    }

    @SubscribeMapping("/chat/{id}")
    public void chatroom(@DestinationVariable String id, HelloMessage message) {
        System.out.println("/chat/" + id);
        System.out.println(message.getName());
        simpMessagingTemplate.convertAndSend("/topic/chat/" + id, message);
    }


}

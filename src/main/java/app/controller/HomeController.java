package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

//    @RequestMapping("/")
//    public String index() {
//        return "home";
//    }
//
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception {
//        System.out.println("きた。");
//        Thread.sleep(3000);
//        return new Greeting("Hello, " + message.getName());
//    }

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

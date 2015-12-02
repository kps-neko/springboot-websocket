package app.controller.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 * Created by s-wada on 2015/12/02.
 * 一般ユーザ向けのWebSocketAPI
 */
@Controller
@MessageMapping("/stduser")
public class StdWebSocketAPI {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    // 通常のSpringMVCとの違いとして、websocketでは @RequestMapping -> @MessageMapping とする。
    @MessageMapping("/hello")
    @SendToUser // 送信者に対する配信。
//    @SendTo // こちらにした場合は全配信。
    public ShortReturnMessage greeting(ShortMessage message) throws Exception {
        ShortReturnMessage msg = new ShortReturnMessage("Hello, " + message.getContent());
        return msg;
    }

    @SubscribeMapping("/chat/{id:[1-5]}")
    public void chatroom(@DestinationVariable String id, ShortMessage message) {
        System.out.println("/chat/" + id);
        System.out.println(message.getContent());
        simpMessagingTemplate.convertAndSend("/topic/stduser/chat/" + id, message);
    }

    static public class ShortReturnMessage {
        private String content;

        public ShortReturnMessage(String content) {
            this.content = content;
        }

        /**
         * Gets content.
         *
         * @return the content
         */
        public String getContent() {
            return content;
        }
    }

    static public class ShortMessage {
        private String content;

        /**
         * Gets content.
         *
         * @return the content
         */
        public String getContent() {
            return content;
        }
    }
}

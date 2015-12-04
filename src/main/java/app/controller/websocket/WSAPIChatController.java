package app.controller.websocket;

import app.form.ChatMessage;
import app.form.WebSocketResult;
import app.service.ChatService;
import app.service.WebSocketValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by s-wada on 2015/12/02.
 * 一般ユーザ向けのWebSocketAPI
 */
@Controller
@EnableAutoConfiguration
// 通常のSpringMVCとの違いとして、websocketでは @RequestMapping -> @MessageMapping とする。
@MessageMapping("/stduser")
public class WSAPIChatController {
    private static final Logger log = LoggerFactory.getLogger(WSAPIChatController.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ChatService chatService;

    @Autowired
    private WebSocketValidationService webSocketValidationService;

    /**
     * Chatroom.
     *
     * @param id      the id
     * @param message the message
     * @throws Exception the exception
     */
    @SubscribeMapping(value = "/chat/{id:[1-5]}")
    public void chatroom(@DestinationVariable String id, ChatMessage message) throws Exception {
        // WebSocketのバリデーションはフォームに結びつかないので、専用のバリデーションメソッドを使います。
        // バリデーションエラーは例外ではないのでメッセージとしてユーザに送信します。
        Map errors = webSocketValidationService.validate(message);
        WebSocketResult result = (errors != null)
            ? new WebSocketResult(false, errors)
            : new WebSocketResult(true, chatService.getReturnMessage(message));

        simpMessagingTemplate.convertAndSend("/topic/stduser/chat/" + id, result);
    }

    /**
     * The type Short return message.
     */
    static public class ShortReturnMessage {
        private String content;

        /**
         * Instantiates a new Short return message.
         *
         * @param content the content
         */
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

}

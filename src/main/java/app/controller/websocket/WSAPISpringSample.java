package app.controller.websocket;

import app.form.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;

/**
 * Created by s-wada on 2015/12/04.
 */
public class WSAPISpringSample {
    /**
     * Greeting short return message.
     *
     * @param message the message
     * @return the short return message
     * @throws Exception the exception
     */
    @MessageMapping("/hello")
    @SendToUser // 送信者に対する配信。
//    @SendTo // こちらにした場合は全配信。
    public ChatMessage greeting(ChatMessage message) throws Exception {
        ChatMessage msg = new ChatMessage("Hello, " + message.getContent());
        return msg;
    }
}

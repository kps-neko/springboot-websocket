package app.service;

import app.exception.BusinessErrorException;
import app.form.ChatMessage;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

/**
 * Created by s-wada on 2015/12/04.
 */
@Service
public class ChatService {

    /**
     * Gets return message.
     *
     * @param message the message
     * @return the return message
     */
    public ChatMessage getReturnMessage(ChatMessage message) {
        if (message.getContent().equals("throw")) {
            throw  new BusinessErrorException("エラーですよ！！！");
        }
        return message;
    }
}

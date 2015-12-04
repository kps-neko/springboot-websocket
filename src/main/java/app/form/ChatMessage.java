package app.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * The type Chat message.
 */
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = -6308951591927634235L;

    /**
     * Instantiates a new Chat message.
     */
    public ChatMessage() {
    }

    /**
     * Instantiates a new Chat message.
     *
     * @param content the content
     */
    public ChatMessage(String content) {
        this.content = content;
    }

    @Length(max = 15,message = "メッセージは15文字までです。")
    @NotNull(message = "メッセージが有りません。")
    @NotBlank(message = "メッセージが空です。")
    private String content;

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

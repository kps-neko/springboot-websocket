package app.form;

/**
 * Created by s-wada on 2015/12/03.
 */
public class ApplicationError {
    public ApplicationError(String content) {
        this.content = content;
    }

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

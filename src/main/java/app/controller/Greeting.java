package app.controller;

/**
 * Created by s-wada on 2015/11/27.
 */
public class Greeting {
    private String content;

    public Greeting(String content) {
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

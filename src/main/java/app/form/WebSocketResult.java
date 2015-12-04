package app.form;

/**
 * WebSocketの通信結果を返すコンテナです。
 */
public class WebSocketResult {
    private boolean success;
    private Object body;

    /**
     * Instantiates a new Web socket result.
     *
     * @param success the success
     * @param body    the body
     */
    public WebSocketResult(boolean success, Object body) {
        this.success = success;
        this.body = body;
    }

    /**
     * Is success boolean.
     *
     * @return the boolean
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets body.
     *
     * @return the body
     */
    public Object getBody() {
        return body;
    }
}

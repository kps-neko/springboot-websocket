package app.exception;

/**
 * 業務処理例外クラス
 *
 * @author a -numadate
 */
public class BusinessErrorException extends RuntimeException {
    /**
     * Instantiates a new Business error exception.
     */
    public BusinessErrorException() {
        super();
    }

    /**
     * Instantiates a new Business error exception.
     *
     * @param message the message
     */
    public BusinessErrorException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Business error exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public BusinessErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Business error exception.
     *
     * @param cause the cause
     */
    public BusinessErrorException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Business error exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected BusinessErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

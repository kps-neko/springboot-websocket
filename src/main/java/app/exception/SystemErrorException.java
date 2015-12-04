package app.exception;

/**
 * システム例外クラス
 *
 * @author a -numadate
 */
public class SystemErrorException extends RuntimeException {
    /**
     * Instantiates a new System error exception.
     */
    public SystemErrorException() {
        super();
    }

    /**
     * Instantiates a new System error exception.
     *
     * @param message the message
     */
    public SystemErrorException(String message) {
        super(message);
    }

    /**
     * Instantiates a new System error exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public SystemErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new System error exception.
     *
     * @param cause the cause
     */
    public SystemErrorException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new System error exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    protected SystemErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

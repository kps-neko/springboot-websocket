package app.exception;

/**
 * 業務処理例外クラス
 *
 * @author a-numadate
 */
public class BusinessErrorException extends RuntimeException {
    public BusinessErrorException() {
        super();
    }

    public BusinessErrorException(String message) {
        super(message);
    }

    public BusinessErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessErrorException(Throwable cause) {
        super(cause);
    }

    protected BusinessErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

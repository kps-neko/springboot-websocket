package app.handler;

import app.exception.BusinessErrorException;
import app.exception.SystemErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.management.RuntimeErrorException;

/**
 * The type Exception handling controller advice.
 */
@ControllerAdvice
public class ExceptionHandlingControllerAdvice {
    /**
     * The Logger.
     */
    protected Logger logger;

    /**
     * Instantiates a new Exception handling controller advice.
     */
    public ExceptionHandlingControllerAdvice() {
        logger = LoggerFactory.getLogger(getClass());
    }

    // ****************************************
    // アプリケーション共通のエラーハンドリング
    // ****************************************

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    /**
     * BusinessErrorExceptionがスローされたら、400を返却する
     *
     * @param exception the exception
     * @return the string
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessErrorException.class)
    public String businessError(Exception exception) {
        exception.printStackTrace();
        logger.error("Request raised " + exception.getClass().getSimpleName());
        return "businessError";
    }

    /**
     * SystemErrorExceptionがスローされたら、500を返却する
     *
     * @param exception the exception
     * @return the string
     */
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SystemErrorException.class)
    public String systemError(Exception exception) {
        exception.printStackTrace();
        logger.error("Request raised " + exception.getClass().getSimpleName());
        return "error";
    }

    /**
     * RuntimeErrorExceptionがスローされたら、500を返却する
     *
     * @param exception the exception
     * @return the string
     */
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeErrorException.class)
    public String databaseError(Exception exception) {
        exception.printStackTrace();
        logger.error("Request raised " + exception.getClass().getSimpleName());
        return "error";
    }

    /**
     * WebSocketのBusinessErrorExceptionのメッセージ部をクライアントに返却します。
     *
     * @param exception 例外
     * @return クライアントに例外メッセージを送信します。
     */
    @MessageExceptionHandler
    @SendToUser(destinations = "/queue/error")
    public ErrorMessage wsBusinessError(BusinessErrorException exception) {
        exception.printStackTrace();
        logger.error("Request raised " + exception.getClass().getSimpleName());
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    /**
     * WebSocketのSystemErrorExceptionのメッセージ部をクライアントに返却します。
     *
     * @param exception 例外
     * @return クライアントに例外メッセージを送信します。
     */
    @MessageExceptionHandler
    @SendToUser(destinations = "/queue/error")
    public ErrorMessage wsSystemError(SystemErrorException exception) {
        exception.printStackTrace();
        logger.error("Request raised " + exception.getClass().getSimpleName());
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    /**
     * WebSocketのRuntimeErrorExceptionのメッセージ部をクライアントに返却します。
     *
     * @param exception 例外
     * @return クライアントに例外メッセージを送信します。
     */
    @MessageExceptionHandler
    @SendToUser(destinations = "/queue/error")
    public ErrorMessage wsDatabaseError(RuntimeErrorException exception) {
        exception.printStackTrace();
        logger.error("Request raised " + exception.getClass().getSimpleName());
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    /**
     * WebSocket用エラーメッセージクラス。
     * The type Error message.
     */
    static class ErrorMessage {
        private int errorCode;
        private String message;

        /**
         * Gets error code.
         *
         * @return the error code
         */
        public int getErrorCode() {
            return errorCode;
        }

        /**
         * Instantiates a new Error message.
         *
         * @param errorCode the error code
         * @param message   the message
         */
        public ErrorMessage(int errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

        /**
         * Gets message.
         *
         * @return the message
         */
        public String getMessage() {
            return message;
        }
    }
}

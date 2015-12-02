package app.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import app.exception.BusinessErrorException;
import app.exception.SystemErrorException;

import javax.management.RuntimeErrorException;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice {
    protected Logger logger;

    public ExceptionHandlingControllerAdvice() {
        logger = LoggerFactory.getLogger(getClass());
    }

    // ****************************************
    // アプリケーション共通のエラーハンドリング
    // ****************************************

    /**
     *  BusinessErrorExceptionがスローされたら、400を返却する
     */
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessErrorException.class)
    public String businessError(Exception exception){
        exception.printStackTrace();
        logger.error("Request raised " + exception.getClass().getSimpleName());
        return "businessError";
    }

    /**
     * SystemErrorExceptionがスローされたら、500を返却する
     *
     */
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SystemErrorException.class)
    public String systemError(Exception exception) {
        exception.printStackTrace();
        logger.error("Request raised " + exception.getClass().getSimpleName());
        return "error";
    }

    /**
     * RuntimeErrorExceptionがスローされたら、500を返却する
     *
     */
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeErrorException.class)
    public String databaseError(Exception exception) {
        exception.printStackTrace();
        logger.error("Request raised " + exception.getClass().getSimpleName());
        return "error";
    }
}

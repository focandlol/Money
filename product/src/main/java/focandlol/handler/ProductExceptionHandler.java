package focandlol.handler;

import focandlol.exception.ErrorResponse;
import focandlol.exception.error.BaseCustomException;
import focandlol.exception.error.ProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static focandlol.exception.errorcode.GlobalErrorCode.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class ProductExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductException.class)
    public ErrorResponse handleProductException(ProductException e) {
        log.error(e.getMessage());
        return new ErrorResponse(e.getErrorCode(),e.getErrorMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaseCustomException.class)
    public ErrorResponse handleBaseCustomException(BaseCustomException e) {
        log.error(e.getMessage());
        return new ErrorResponse(e.getErrorCode(),e.getErrorMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleCustomException(Exception e) {
        log.error(e.getMessage());
        return new ErrorResponse(INTERNAL_SERVER_ERROR,INTERNAL_SERVER_ERROR.getDescription());
    }
}

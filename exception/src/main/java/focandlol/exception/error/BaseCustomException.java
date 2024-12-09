package focandlol.exception.error;

import focandlol.exception.errorcode.ErrorCode;
import lombok.Getter;

@Getter
public abstract class BaseCustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String errorMessage;

    public BaseCustomException(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}


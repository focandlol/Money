package focandlol.exception.error;

import focandlol.exception.errorcode.UserErrorCode;
import lombok.*;

@Getter
public class UserException extends BaseCustomException {

    public UserException(UserErrorCode userErrorCode) {
        super(userErrorCode, userErrorCode.getDescription());
    }

}

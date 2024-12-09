package focandlol.exception.error;

import focandlol.exception.errorcode.ProductErrorCode;
import lombok.*;

@Getter
public class ProductException extends BaseCustomException {

    public ProductException(ProductErrorCode productErrorCode) {
        super(productErrorCode, productErrorCode.getDescription());
    }


}

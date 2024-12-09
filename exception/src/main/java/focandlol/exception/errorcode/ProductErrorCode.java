package focandlol.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductErrorCode implements ErrorCode {

    ALREADY_PRODUCT("해당 상품이 이미 있습니다"),
    WRONG_PRODUCT_CODE("잘못된 상품 코드입니다"),
    WRONG_ORGANIZATION_CODE("잘못된 기업 코드입니다");
    private final String description;
}

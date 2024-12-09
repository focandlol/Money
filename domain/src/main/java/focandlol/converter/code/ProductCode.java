package focandlol.converter.code;

import focandlol.exception.error.ProductException;
import focandlol.exception.errorcode.ProductErrorCode;

public enum ProductCode {
    NONE("000"),
    PRODUCT_ONE("001"),
    PRODUCT_TWO("002");

    private final String code;

    ProductCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ProductCode findProduct(String code) {
        for (ProductCode product : ProductCode.values()) {
            if (product.code.equals(code)) {
                return product;
            }
        }
        throw new ProductException(ProductErrorCode.WRONG_PRODUCT_CODE);
    }
}

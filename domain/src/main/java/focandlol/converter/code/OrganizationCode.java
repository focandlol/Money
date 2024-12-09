package focandlol.converter.code;

import focandlol.exception.error.ProductException;
import focandlol.exception.errorcode.ProductErrorCode;


public enum OrganizationCode {
    NONE("00000"),
    ORGANIZATION_ONE("00001"),
    ORGANIZATION_TWO("00002");

    private final String code;

    OrganizationCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static OrganizationCode findOrganization(String code) {
        for (OrganizationCode organization : OrganizationCode.values()) {
            if (organization.code.equals(code)) {
                return organization;
            }
        }
        throw new ProductException(ProductErrorCode.WRONG_ORGANIZATION_CODE);
    }

    @Override
    public String toString() {
        return name(); // 캐시 키로 사용할 값 반환
    }
}

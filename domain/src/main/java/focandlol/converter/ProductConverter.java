package focandlol.converter;

import focandlol.converter.code.ProductCode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ProductConverter implements AttributeConverter<ProductCode, String>{
    @Override
    public String convertToDatabaseColumn(ProductCode code) {
        if (code == null) {
            return ProductCode.NONE.getCode();
        }
        return code.getCode();
    }

    @Override
    public ProductCode convertToEntityAttribute(String code) {
        if (code == null || code.isEmpty()) {
            return ProductCode.NONE;
        }
        return ProductCode.findProduct(code);
    }
}

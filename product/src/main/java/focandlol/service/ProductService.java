package focandlol.service;

import focandlol.converter.code.OrganizationCode;
import focandlol.dto.FindProductInfoDto;
import focandlol.dto.ProductInfoDto;

import java.util.List;

public interface ProductService {

    void save(ProductInfoDto.Request request);
    List<FindProductInfoDto> findProductInfo(OrganizationCode organizationCode);
}

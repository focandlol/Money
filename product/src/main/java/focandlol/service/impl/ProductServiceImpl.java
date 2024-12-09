package focandlol.service.impl;

import focandlol.converter.code.OrganizationCode;
import focandlol.converter.code.ProductCode;
import focandlol.domain.ProductInfo;
import focandlol.dto.FindProductInfoDto;
import focandlol.dto.ProductInfoDto;
import focandlol.exception.error.ProductException;
import focandlol.repository.ProductInfoRepository;
import focandlol.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static focandlol.exception.errorcode.ProductErrorCode.ALREADY_PRODUCT;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final CacheManager redisCacheManager;

    private final ProductInfoRepository productInfoRepository;

    @Override
    public void save(ProductInfoDto.Request request) {
        if(check(request)){
            throw new ProductException(ALREADY_PRODUCT);
        }
        productInfoRepository.save(request.toEntity());

        /**
         * 해당 기관 상품 정보가 추가되면 캐시 삭제
         */
        redisCacheManager.getCache("ORG_CODE").evict(request.getOrganizationCode());
    }

    private boolean check(ProductInfoDto.Request request) {
        Optional<ProductInfo> pi = productInfoRepository.findByProductCodeAndOrganizationCode(
                ProductCode.findProduct(request.getProductCode()), OrganizationCode.findOrganization(request.getOrganizationCode())
        );

        if(pi.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    @Cacheable(key = "#organizationCode.code", value = "ORG_CODE")
    @Transactional(readOnly = true)
    public List<FindProductInfoDto> findProductInfo(OrganizationCode organizationCode) {
        return productInfoRepository.findByOrganizationCode(organizationCode)
                .stream()
                .map(a -> FindProductInfoDto.from(a))
                .collect(Collectors.toList());
    }

}

package focandlol.repository;

import focandlol.converter.code.OrganizationCode;
import focandlol.converter.code.ProductCode;
import focandlol.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {
    Optional<ProductInfo> findByProductCodeAndOrganizationCode(ProductCode productCode, OrganizationCode organizationCode);
    List<ProductInfo> findByOrganizationCode(OrganizationCode organizationCode);
}

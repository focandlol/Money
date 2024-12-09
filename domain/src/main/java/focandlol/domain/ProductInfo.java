package focandlol.domain;

import focandlol.converter.OrganizationConverter;
import focandlol.converter.ProductConverter;
import focandlol.converter.code.OrganizationCode;
import focandlol.converter.code.ProductCode;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_INFO",
        indexes = {
                @Index(name = "idx_org_cd", columnList = "org_cd"),
                @Index(name = "idx_prod_cd", columnList = "prod_cd")
        }
        )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = OrganizationConverter.class)
    @Column(name = "org_cd")
    private OrganizationCode organizationCode;

    @Convert(converter = ProductConverter.class)
    @Column(name = "prod_cd")
    private ProductCode productCode;

    @Column(name = "prod_nm")
    private String productName;

    @Column(name = "prod_min_intr")
    private Double productMinimumInterest;

    @Column(name = "prod_max_intr")
    private Double productMaximumInterest;
}
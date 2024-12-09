package focandlol.dto;

import focandlol.domain.ProductInfo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindProductInfoDto {

    private String organizationCode;
    private String productCode;
    private Double productMaximumInterest;
    private Double productMinimumInterest;
    private String productName;

    public static FindProductInfoDto from(ProductInfo productInfo){
        return FindProductInfoDto
                .builder()
                .organizationCode(productInfo.getOrganizationCode().getCode())
                .productCode(productInfo.getProductCode().getCode())
                .productMaximumInterest(productInfo.getProductMaximumInterest())
                .productMinimumInterest(productInfo.getProductMinimumInterest())
                .productName(productInfo.getProductName())
                .build();
    }
}

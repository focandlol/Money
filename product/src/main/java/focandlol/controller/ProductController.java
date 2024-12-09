package focandlol.controller;

import focandlol.converter.code.OrganizationCode;
import focandlol.dto.ProductInfoDto;
import focandlol.response.CustomResponse;
import focandlol.response.ResponseCode;
import focandlol.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fintech/v1/product")
@RequiredArgsConstructor
@Tag(name = "Product Controller", description = "상품과 관련된 API를 제공합니다.")
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "기관으로 상품 정보 조회",
            description = "상품 조회"
    )
    @GetMapping("{organizationCode}")
    public ResponseEntity<CustomResponse> findProduct(@PathVariable OrganizationCode organizationCode){
        return ResponseEntity.ok().body(new CustomResponse(productService.findProductInfo(organizationCode),ResponseCode.SUCCESS));
    }

    @Operation(
            summary = "금융사로 상품 정보 보내기",
            description = "상품 정보 보내기"
    )
    @PostMapping("/information")
    public ResponseEntity<CustomResponse> information(@RequestBody @Parameter(description = "product") ProductInfoDto.Request request){
        productService.save(request);
        return ResponseEntity.ok(new CustomResponse<>(ResponseCode.SUCCESS));
    }
}

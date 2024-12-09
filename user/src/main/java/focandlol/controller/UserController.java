package focandlol.controller;

import focandlol.dto.UserInfoDto;
import focandlol.response.CustomResponse;
import focandlol.service.Impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static focandlol.response.ResponseCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fintech/v1/user")
@Tag(name = "User Controller", description = "유저와 관련된 API를 제공합니다.")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @Operation(
            summary = "userKey 받는 api",
            description = "유저 정보를 받는 api"
    )
    @PostMapping("/information")
    public ResponseEntity<CustomResponse> user(@RequestBody UserInfoDto.Request request) {
        return ResponseEntity.ok(new CustomResponse<>(userServiceImpl.save(request),SUCCESS));
    }

    @Operation(
            summary = "유저 정보 조회 api",
            description = "유저 정보를 조회하는 api"
    )
    @GetMapping("/private-info/{userKey}")
    public ResponseEntity<CustomResponse> privateInfo(@PathVariable String userKey) {
        return ResponseEntity.ok(new CustomResponse(userServiceImpl.findUserInfo(userKey),
                SUCCESS));
    }
}

package focandlol.dto;

import focandlol.domain.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class UserInfoDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {

        @Schema(description = "UserName", example = "kkm")
        private String userName;
        @Schema(description = "UserRegistrationNum", example = "11111111111")
        private String userRegistrationNumber;
        @Schema(description = "userIncome", example = "1111111111")
        private Long userIncomeAmount;

        public UserInfo toEntity(String key) {
            return UserInfo.builder()
                    .userName(this.userName)
                    .userRegistrationNumber(this.userRegistrationNumber)
                    .userIncomeAmount(this.userIncomeAmount)
                    .userKey(key)
                    .build();

        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private String userKey;
    }
}

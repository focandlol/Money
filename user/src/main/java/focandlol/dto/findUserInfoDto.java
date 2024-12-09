package focandlol.dto;

import focandlol.domain.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class findUserInfoDto {
    private String userKey;
    private String userRegistrationNumber;

    public static findUserInfoDto from(UserInfo userInfo) {
        return new findUserInfoDto(userInfo.getUserKey(), userInfo.getUserRegistrationNumber());
    }
}

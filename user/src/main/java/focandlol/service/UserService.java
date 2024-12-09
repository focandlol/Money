package focandlol.service;

import focandlol.dto.UserInfoDto;
import focandlol.dto.findUserInfoDto;

public interface UserService {
    UserInfoDto.Response save(UserInfoDto.Request request);
    findUserInfoDto findUserInfo(String userKey);
}

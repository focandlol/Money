package focandlol.service.Impl;

import focandlol.domain.UserInfo;
import focandlol.dto.findUserInfoDto;
import focandlol.dto.UserInfoDto;
import focandlol.exception.error.UserException;
import focandlol.repository.UserInfoRepository;
import focandlol.service.UserService;
import focandlol.util.GenerateKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static focandlol.exception.errorcode.UserErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserInfoRepository userInfoRepository;
    private final GenerateKey generateKey;

    @Override
    @Transactional
    public UserInfoDto.Response save(UserInfoDto.Request request){
        String userKey = generateKey.generateUserKey();
        userInfoRepository.save(request.toEntity(userKey));
        return new UserInfoDto.Response(userKey);
    }

    @Override
    @Transactional(readOnly = true)
    public findUserInfoDto findUserInfo(String userKey){
        UserInfo userInfo = userInfoRepository.findByUserKey(userKey)
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));

        return findUserInfoDto.from(userInfo);

    }
}

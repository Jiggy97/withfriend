package kumbayah.withfriend.service.user;

import kumbayah.withfriend.dto.kakao.KakaoDTO;
import kumbayah.withfriend.dto.user.UserDTO;
import kumbayah.withfriend.entity.user.UserEntity;
import kumbayah.withfriend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void register(KakaoDTO kakaoDTO) {
        UserEntity isFirstLogin = userRepository.findByUserId(kakaoDTO.getId());
        if (isFirstLogin == null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(kakaoDTO.getId());
            userDTO.setNickname(kakaoDTO.getNickname());

            UserEntity userEntity = UserEntity.toSaveEntity(userDTO);
            userRepository.save(userEntity);
        }
    }

    public UserDTO findByUserId(long userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        return UserDTO.toUserDTO(userEntity);
    }

    public void chargePoint(long userId, double chargePoint) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        chargePoint += userEntity.getPoint();
        userEntity.setPoint(chargePoint);
        userRepository.save(userEntity);
    }
}

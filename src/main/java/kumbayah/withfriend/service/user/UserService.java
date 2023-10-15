package kumbayah.withfriend.service.user;

import kumbayah.withfriend.dto.kakao.KakaoDTO;
import kumbayah.withfriend.dto.trustMarketplace.GoodsDTO;
import kumbayah.withfriend.dto.user.UserDTO;
import kumbayah.withfriend.dto.user.UserTradeRequestDataDTO;
import kumbayah.withfriend.entity.trustMarketplace.GoodsEntity;
import kumbayah.withfriend.entity.user.UserEntity;
import kumbayah.withfriend.repository.trustMarketplace.TrustMarketplaceRepository;
import kumbayah.withfriend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrustMarketplaceRepository trustMarketplaceRepository;

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

    public void Trade(UserTradeRequestDataDTO userTradeRequestDataDTO) {
        // UserEntity를 통해 거래 후 point update
        UserEntity buyerEntity = userRepository.findByUserId(userTradeRequestDataDTO.getBuyerId());
        chargePoint(buyerEntity.getUserId(), -userTradeRequestDataDTO.getGoodsPrice());

        Optional<GoodsEntity> optionalGoodsEntity = trustMarketplaceRepository.findById(userTradeRequestDataDTO.getGoodsId());
        if (optionalGoodsEntity.isPresent()) {
            GoodsEntity goodsEntity = optionalGoodsEntity.get();
            UserEntity sellerEntity = userRepository.findByUserId(goodsEntity.getUserId());
            chargePoint(sellerEntity.getUserId(), userTradeRequestDataDTO.getGoodsPrice());

            // goodsEntity를 통해 거래 후 stock update
            // 구매한 개수에 따라 달라지게 설계하자. ###########################
            trustMarketplaceRepository.
        } else {
            // 오류 발생
            System.out.println("없는 상품을 거래하는 오류 발생");
        }


        // TradeEntity를 통해 거래 기록 create

        // transaction 을 통해 db에 반영
    }
}

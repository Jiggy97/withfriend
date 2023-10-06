package kumbayah.withfriend.repository.user;

import kumbayah.withfriend.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(long userId);
}

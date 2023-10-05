package kumbayah.withfriend.entity.user;

import jakarta.persistence.*;
import kumbayah.withfriend.dto.user.UserDTO;

@Entity
@Table(name = "user_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long userId;

    @Column
    private String nickname;

    @Column
    private double point;

    public UserEntity() {}

    public long getId() { return id; }
    public long getUserId() { return userId; }
    public String getNickname() { return nickname; }
    public double getPoint() { return point; }

    public void setId(long id) { this.id = id; }
    public void setUserId(long userId) { this.userId = userId; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setPoint(double point) { this.point = point; }

    public static UserEntity toSaveEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userDTO.getUserId());
        userEntity.setNickname(userDTO.getNickname());
        userEntity.setPoint(0);
        return userEntity;
    }
}

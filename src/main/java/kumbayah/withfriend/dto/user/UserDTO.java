package kumbayah.withfriend.dto.user;

import kumbayah.withfriend.entity.user.UserEntity;

public class UserDTO {
    private long id;

    private long userId;

    private String nickname;

    private double point;

    public UserDTO() {}

    public long getId() { return id; }
    public long getUserId() { return userId; }
    public String getNickname() { return nickname; }
    public double getPoint() { return point; }

    public void setId(long id) { this.id = id; }
    public void setUserId(long userId) { this.userId = userId; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setPoint(double point) { this.point = point; }

    public static UserDTO toUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setNickname(userEntity.getNickname());
        userDTO.setPoint(userEntity.getPoint());

        return userDTO;
    }
}

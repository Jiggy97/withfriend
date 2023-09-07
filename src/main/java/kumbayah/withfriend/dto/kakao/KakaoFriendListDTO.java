package kumbayah.withfriend.dto.kakao;

import java.util.List;

public class KakaoFriendListDTO {
    private long id;
    private List<String> friendList;

    public KakaoFriendListDTO() {}

    public long getId() { return id; }
    public List<String> getFriendList() { return friendList; }

    public void setId(long id) { this.id = id; }
    public void setFriendList(List<String> friendList) { this.friendList = friendList; }
}

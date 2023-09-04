package kumbayah.withfriend.dto.kakao;

import java.util.List;

public class KakaoFriendsInfoDTO {
    private List<Friend> elements;
    private int total_count;

    public KakaoFriendsInfoDTO(List<Friend> elements, int total_count) {
        this.elements = elements;
        this.total_count = total_count;
    }

    public List<Friend> getElements() { return elements; }
    public int getTotal_count() { return total_count; }

    public void setElement(List<Friend> elements) { this.elements = elements; }
    public void setTotal_count(int total_count) { this.total_count = total_count; }

    public static class Friend {
        private long id;
        private String uuid;
        private String profile_nickname;

        public Friend(long id, String uuid, String profile_nickname) {
            this.id = id;
            this.uuid = uuid;
            this.profile_nickname = profile_nickname;
        }

        public long getId() { return id; }
        public String getUuid() { return uuid; }
        public String getProfile_nickname() { return profile_nickname; }

        public void setId(long id) { this.id = id; }
        public void setUuid(String uuid) { this.uuid = uuid; }
        public void setProfile_nickname(String profile_nickname) { this.profile_nickname = profile_nickname; }
    }
}

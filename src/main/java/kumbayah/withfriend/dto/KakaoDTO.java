package kumbayah.withfriend.dto;

public class KakaoDTO {
    private long id;
    private String nickname;
//    private String profileImageUrl;


    public KakaoDTO(long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
//        this.profileImageUrl = profileImageUrl;
    }

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

//    public String getProfileImageUrl() {
//        return profileImageUrl;
//    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

//    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }


}

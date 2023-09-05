package kumbayah.withfriend.dto.kakao.trust_marketplace;

import kumbayah.withfriend.entity.trust_marketplace.GoodsEntity;

public class GoodsListDTO {
    private String name;
    private String kakaoNickname;
    private double price;
    private String description;

    public GoodsListDTO() {}

    public String getName() { return name; }
    public String getKakaoNickname() { return kakaoNickname; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }

    public void setName(String name) { this.name = name; }
    public void setKakaoNickname(String kakaoNickname) { this.kakaoNickname = kakaoNickname; }
    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }

    public static GoodsListDTO toGoodsListDTO(GoodsEntity goodsEntity) {
        GoodsListDTO goods = new GoodsListDTO();
        goods.setKakaoNickname(goodsEntity.getKakaoNickname());
        goods.setName(goodsEntity.getName());
        goods.setPrice(goodsEntity.getPrice());
        goods.setDescription(goodsEntity.getDescription());

        return goods;
    }
}

package kumbayah.withfriend.entity.trust_marketplace;


import jakarta.persistence.*;
import kumbayah.withfriend.dto.kakao.trust_marketplace.GoodsDTO;
import kumbayah.withfriend.entity.BaseEntity;

@Entity
@Table(name = "goods_table")
public class GoodsEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String kakaoNickname;

    @Column
    private String name;

    @Column
    private double price;

    @Column
    private String description;

    public GoodsEntity() {}
    // 생성자 내부에 변수를 넣지 않는 이유를 더 자세히 알면 좋을거 같아

    public long getId() { return id; }
    public String getKakaoNickname() { return kakaoNickname; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }

    public void setId(long id) { this.id = id; }
    public void setKakaoNickname(String kakaoNickname) { this.kakaoNickname = kakaoNickname; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }

    public static GoodsEntity toSaveEntity(GoodsDTO goods, String kakaoNickname) {
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setKakaoNickname(kakaoNickname);
        goodsEntity.setName(goods.getName());
        goodsEntity.setPrice(goods.getPrice());
        goodsEntity.setDescription(goods.getDescription());

        return goodsEntity;
    }
}

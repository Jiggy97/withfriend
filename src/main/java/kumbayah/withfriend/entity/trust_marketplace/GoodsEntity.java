package kumbayah.withfriend.entity.trust_marketplace;


import jakarta.persistence.*;
import kumbayah.withfriend.dto.trust_marketplace.GoodsDTO;
import kumbayah.withfriend.entity.BaseEntity;

@Entity
@Table(name = "goods_table")
public class GoodsEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long userId;

    @Column
    private String name;

    @Column
    private String seller;

    @Column
    private double price;

    @Column
    private String description;

    @Column
    private int stock;

    public GoodsEntity() {}
    // 생성자 내부에 변수를 넣지 않는 이유를 더 자세히 알면 좋을거 같아

    public long getId() { return id; }
    public long getUserId() { return userId; }
    public String getSeller() { return seller; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public int getStock() { return stock; }

    public void setId(long id) { this.id = id; }
    public void setUserId(long userId) { this.userId = userId; }
    public void setSeller(String seller) { this.seller = seller; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
    public void setStock(int stock) { this.stock = stock; }

    public static GoodsEntity toSaveEntity(GoodsDTO goods, String seller, long userId) {
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setUserId(userId);
        goodsEntity.setSeller(seller);
        goodsEntity.setName(goods.getName());
        goodsEntity.setPrice(goods.getPrice());
        goodsEntity.setDescription(goods.getDescription());
        goodsEntity.setStock(goods.getStock());

        return goodsEntity;
    }

    public static GoodsEntity toUpdateEntity(GoodsDTO goods, String seller, long userId) {
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setId(goods.getId());
        goodsEntity.setUserId(userId);
        goodsEntity.setSeller(seller);
        goodsEntity.setName(goods.getName());
        goodsEntity.setPrice(goods.getPrice());
        goodsEntity.setDescription(goods.getDescription());
        goodsEntity.setStock(goods.getStock());

        return goodsEntity;
    }
}

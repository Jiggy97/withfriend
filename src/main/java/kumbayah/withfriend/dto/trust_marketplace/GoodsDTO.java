package kumbayah.withfriend.dto.trust_marketplace;

import kumbayah.withfriend.entity.trust_marketplace.GoodsEntity;

public class GoodsDTO {
    private long id;
    private String name;
    private String seller;
    private double price;
    private String description;
    private int stock;

    public GoodsDTO() {}

    public long getId() { return id; }
    public String getName() { return name; }
    public String getSeller() { return seller; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public int getStock() { return stock; }

    public void setId(long id) {this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSeller(String seller) { this.seller = seller; }
    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
    public void setStock(int stock) { this.stock = stock; }

    public static GoodsDTO toGoodsDTO(GoodsEntity goodsEntity) {
        GoodsDTO goods = new GoodsDTO();
        goods.setId(goodsEntity.getId());
        goods.setSeller(goodsEntity.getSeller());
        goods.setName(goodsEntity.getName());
        goods.setPrice(goodsEntity.getPrice());
        goods.setDescription(goodsEntity.getDescription());
        goods.setStock(goodsEntity.getStock());

        return goods;
    }
}

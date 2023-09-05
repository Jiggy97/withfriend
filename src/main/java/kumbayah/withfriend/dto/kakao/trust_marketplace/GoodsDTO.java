package kumbayah.withfriend.dto.kakao.trust_marketplace;

public class GoodsDTO {
    private String name;
    private double price;
    private String description;

    public GoodsDTO() {}

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
}

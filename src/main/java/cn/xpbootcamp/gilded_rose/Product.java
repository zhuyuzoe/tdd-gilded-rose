package cn.xpbootcamp.gilded_rose;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class Product {
    private String name;
    private Integer sellIn;
    private Double quality;
    private Double changeRate;

    public Product(String name, Integer sellIn, Double quality, Double changeRate) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.changeRate = changeRate;
        qualityValidation(quality);
    }

    private void qualityValidation(Double quality) {
        if (quality < 0 || quality > 50) {
            throw new IllegalArgumentException("quality should between 0 and 50");
        }
    }
}

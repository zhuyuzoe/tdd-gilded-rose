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
    private Integer quality;

    public Product(String name, Integer sellIn, Integer quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        qualityValidation(quality);
    }

    private void qualityValidation(Integer quality) {
        if (quality < 0 || quality > 50) {
            throw new IllegalArgumentException("quality should between 0 and 50");
        }
    }
}

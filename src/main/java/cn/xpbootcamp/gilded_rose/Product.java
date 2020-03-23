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
    private ProductType productType;

    public Product(String name, Integer sellIn, Double quality, Double changeRate, ProductType productType) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.changeRate = changeRate;
        this.productType = productType;
        qualityValidation(quality);
        changeRateValidation(changeRate, productType);
    }

    private void changeRateValidation( Double changeRate, ProductType productType) {
        if (productType == ProductType.NORMAL && (changeRate >= 0 || changeRate <= -1) ) {
            throw new IllegalArgumentException("change rate should between -1 and 0 for normals");
        }
        if (productType == ProductType.SPECIAL && (changeRate <= 0 || changeRate >= 1) ) {
            throw new IllegalArgumentException("change rate should between 0 and 1 for specials");
        }
        if (productType == ProductType.LEGEND && changeRate != 1 ) {
            throw new IllegalArgumentException("change rate should be 1 for legends");
        }
    }

    private void qualityValidation(Double quality) {
        if (quality < 0 || quality > 50) {
            throw new IllegalArgumentException("quality should between 0 and 50");
        }
    }
}

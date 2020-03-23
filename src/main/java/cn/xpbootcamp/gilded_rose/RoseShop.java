package cn.xpbootcamp.gilded_rose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoseShop {
    private HashMap<String, Integer> sellIns = new HashMap<>();
    private HashMap<String, Double> values = new HashMap<>();
    private HashMap<String, Double> changeRates = new HashMap<>();

    public void productExpireDays(Product product, Integer day) {
        String name = product.getName();
        Integer sellIn = sellIns.get(name);
        Double changeRate = changeRates.get(name);
        Double quality = values.get(name);
        ProductType productType = product.getProductType();

        updateSellInOfProductWithExpirationDays(day, name, sellIn);
        updateValueOfProductWithExpirationDays(day, name, sellIn, changeRate, quality, productType);
    }

    public void addProduct(Product product) {
        sellIns.put(product.getName(), product.getSellIn());
        values.put(product.getName(), product.getQuality());
        changeRates.put(product.getName(), product.getChangeRate());
    }

    private void updateSellInOfProductWithExpirationDays(Integer day, String name, Integer sellIn) {
        sellIns.put(name, sellIn - day);
    }

    private void updateValueOfProductWithExpirationDays(Integer day, String name, Integer sellIn, Double changeRate, Double quality, ProductType productType) {
        if (productType == ProductType.NORMAL)  {
            if (sellIn - day >= 0) {
                values.put(name, getNormalQualityBeforeExpired(day, changeRate, quality));
            } else {
                values.put(name, getNormalQualityAfterExpired(day, sellIn, changeRate, quality));
            }
        } else if (productType == ProductType.SPECIAL) {
            values.put(name, getSpecialQuality(day, changeRate, quality));
        }

    }

    private double getNormalQualityAfterExpired(Integer day, Integer sellIn, Double changeRate, Double quality) {
        if (changeRate > -0.5) {
            return quality * Math.pow(1 + changeRate, sellIn) * Math.pow(1 + 2 * changeRate, day - sellIn);
        } else {
            return 0;
        }
    }

    private double getNormalQualityBeforeExpired(Integer day, Double changeRate, Double quality) {
        return quality * Math.pow(1 + changeRate, day);
    }

    private double getSpecialQuality(Integer day, Double changeRate, Double quality) {
        double updatedQuality = quality * Math.pow(1 + changeRate, day);
        if (updatedQuality >= 50) {
            return 50;
        } else {
            return updatedQuality;
        }
    }

}

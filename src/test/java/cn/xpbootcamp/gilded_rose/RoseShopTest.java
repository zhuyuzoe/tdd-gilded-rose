package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoseShopTest {

    @Test
    void should_throw_if_quality_is_smaller_than_0() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Milk", 20, -1.0, -0.3, ProductType.NORMAL));
    }

    @Test
    void should_throw_if_quality_is_larger_than_50() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Milk", 20, 70.0, -0.3, ProductType.NORMAL));
    }

    @Test
    void should_decrease_the_quality_if_normal_product_expire_some_days() {
        Product milk = new Product("Milk", 2, 40.0, -0.3, ProductType.NORMAL);
        RoseShop roseShop = new RoseShop();
        roseShop.addProduct(milk);

        roseShop.productExpireDays(milk, 1);
        assertEquals(Integer.valueOf(1), roseShop.getSellIns().get("Milk"));
        assertEquals(Double.valueOf(28.0), roseShop.getValues().get("Milk"));

        roseShop.productExpireDays(milk, 2);
        assertEquals(Integer.valueOf(-1), roseShop.getSellIns().get("Milk"));
        assertEquals(Double.valueOf(7.84), roseShop.getValues().get("Milk"));
    }

    @Test
    void should_larger_than_0_for_quality_when_product_expire_1000_days() {
        Product milk = new Product("Milk", 2, 40.0, -0.3, ProductType.NORMAL);
        RoseShop roseShop = new RoseShop();
        roseShop.addProduct(milk);

        roseShop.productExpireDays(milk, 1000);
        assertTrue(roseShop.getValues().get("Milk") >= 0);
    }

    @Test
    void should_larger_than_0_for_quality_when_product_expire_10_days() {
        Product milk = new Product("Milk", 2, 40.0, -0.8, ProductType.NORMAL);
        RoseShop roseShop = new RoseShop();
        roseShop.addProduct(milk);

        roseShop.productExpireDays(milk, 10);
        assertTrue(roseShop.getValues().get("Milk") >= 0);
    }

    @Test
    void should_smaller_than_50_for_quality_when_product_expire_1000_days() {
        Product agedBrie = new Product("AgedBrie", 2, 40.0, 0.8, ProductType.SPECIAL);
        RoseShop roseShop = new RoseShop();
        roseShop.addProduct(agedBrie);

        roseShop.productExpireDays(agedBrie, 1000);
        assertTrue(roseShop.getValues().get("AgedBrie") <= 50);
    }

    @Test
    void should_smaller_than_50_for_quality_when_product_expire_10_days() {
        Product agedBrie = new Product("AgedBrie", 2, 40.0, 0.3, ProductType.SPECIAL);
        RoseShop roseShop = new RoseShop();
        roseShop.addProduct(agedBrie);

        roseShop.productExpireDays(agedBrie, 10);
        assertTrue(roseShop.getValues().get("AgedBrie") <= 50);
    }

    @Test
    void should_increase_for_quality_when_special_product_expire() {
        Product agedBrie = new Product("AgedBrie", 2, 40.0, 0.2, ProductType.SPECIAL);
        RoseShop roseShop = new RoseShop();
        roseShop.addProduct(agedBrie);

        roseShop.productExpireDays(agedBrie, 1);
        Double agedBrieQualityAfterOneDay = roseShop.getValues().get("AgedBrie");
        assertTrue(agedBrieQualityAfterOneDay >= 40.0);

        roseShop.productExpireDays(agedBrie, 1);
        Double agedBrieQualityAfterTwoDay = roseShop.getValues().get("AgedBrie");
        assertTrue(agedBrieQualityAfterTwoDay >= agedBrieQualityAfterOneDay);
    }

    @Test
    void should_throw_if_change_rate_smaller_than_neg_1_for_normals() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Milk", 20, 70.0, -1.3, ProductType.NORMAL));
    }

    @Test
    void should_throw_if_change_rate_larger_than_0_for_normals() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Milk", 20, 70.0, 0.3, ProductType.NORMAL));
    }

    @Test
    void should_throw_if_change_rate_is_0_for_normals() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Milk", 20, 70.0, 0.0, ProductType.NORMAL));
    }

    @Test
    void should_throw_if_change_rate_smaller_than_0_for_specials() {
        assertThrows(IllegalArgumentException.class, () -> new Product("AgedBrie", 20, 70.0, -0.3, ProductType.SPECIAL));
    }

    @Test
    void should_throw_if_change_rate_larger_than_1_for_specials() {
        assertThrows(IllegalArgumentException.class, () -> new Product("AgedBrie", 20, 70.0, 1.3, ProductType.SPECIAL));
    }

    @Test
    void should_throw_if_change_rate_is_0_for_specials() {
        assertThrows(IllegalArgumentException.class, () -> new Product("AgedBrie", 20, 70.0, 0.0, ProductType.SPECIAL));
    }


}

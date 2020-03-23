package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoseShopTest {

    @Test
    void should_throw_if_quality_is_smaller_than_0() {
        assertThrows(IllegalArgumentException.class, () -> new Product("AgedBrie", 20, -1));
    }

    @Test
    void should_throw_if_quality_is_larger_than_50() {
        assertThrows(IllegalArgumentException.class, () -> new Product("AgedBrie", 20, 70));
    }
}

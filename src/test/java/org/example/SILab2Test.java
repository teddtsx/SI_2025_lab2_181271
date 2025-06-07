package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

public class SILab2Test {

    @Test
    void MultipleConditionTest() {
        List<Item> items = new ArrayList<>();


        //Test 1: TXX  - price > 300
        items.clear();
        items.add(new Item("item1", 1, 350, 0));
        double result = SILab2.checkCart(items, "1234567890123456");
        assertEquals(320.0, result);

        //Test 2: FTX - discount > 0
        items.clear();
        items.add(new Item("item2", 1, 200, 0.1));
        double result1 = SILab2.checkCart(items, "1234567890123456");
        assertEquals(150.0, result1);

        //Test 3: FFT quantity > 10
        items.clear();
        items.add(new Item("item3", 12, 200, 0));
        double result2 = SILab2.checkCart(items, "1234567890123456");
        assertEquals(2370.0, result2);

        //Test 4: FFF
        items.clear();
        items.add(new Item("item4", 1, 200, 0));
        double result3 = SILab2.checkCart(items, "1234567890123456");
        assertEquals(200.0, result3);

    }

    @Test
    void EveryStatementTest() {
        // Test 1: AllItems = Null - test if method correctly detects empty list and throws exception
        Exception exception1 = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(null, "1234567890123456");
        });
        assertEquals("allItems list can't be null!", exception1.getMessage());

        // Test 2: Item with null name - test if method correctly detects null item name and throws exception
        List<Item> items = new ArrayList<>();
        items.add(new Item(null, 1, 100, 0));
        Exception exception2 = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items, "1234567890123456");
        });
        assertEquals("Invalid item!", exception2.getMessage());

        // Test 3: Invalid character in card number - test if method correctly detects invalid characters in card number
        items.clear();
        items.add(new Item("item1", 3, 100, 0));
        Exception exception3 = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items, "123456789012345A");
        });
        assertEquals("Invalid character in card number!", exception3.getMessage());

        // Test 4: Invalid card number - test if method correctly detects card number with less than 16 digits
        Exception exception4 = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items, "1234");
        });
        assertEquals("Invalid card number!", exception4.getMessage());

        // Test 5: Valid cart and items - test if method correctly calculates sum for valid inputs
        items.clear();
        items.add(new Item("item1", 3, 100, 0));
        items.add(new Item("item2", 10, 300, 0.1));
        double result = SILab2.checkCart(items, "1234567890123456");
        // Expected: (100 * 3) + (300 * 0.9 * 10) - 30 = 300 + 2700 - 30 = 2970
        assertEquals(2970.0, result);
    }
}

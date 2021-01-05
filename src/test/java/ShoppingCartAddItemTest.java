import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("Testing add Item to the Sopping card")
class ShoppingCartAddItemTest {

    private static ShoppingCart cart;
    private static ShoppingCart hundredItemsCart;

    @BeforeAll
    static void setUp() {
        cart = new ShoppingCart();
        hundredItemsCart = new ShoppingCart();
    }

    @Test
    @DisplayName("Testing Item title")
    void testItemTitle() {
        //negative cases
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem(null, 1, 1, Item.Type.REGULAR),
                "Test null title is failed");
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("", 1, 1, Item.Type.REGULAR),
                "Test empty title is failed");
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Item With Long Characters Title....", 1, 1, Item.Type.REGULAR),
                "Test more than 32 characters title is failed");

        //positive case
        assertDoesNotThrow(() -> cart.addItem("Correct Title", 1, 1, Item.Type.REGULAR),
                "Test correct title character title is failed");
    }

    @Test
    @DisplayName("Testing Item price")
    void testItemPrice() {
        //negative cases
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Item 0 price", 0, 1, Item.Type.REGULAR),
                "Test 0 price is failed");
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Item -3 price", -3, 1, Item.Type.REGULAR),
                "Test -3 price is failed");
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Item 1001 price", 1001, 1, Item.Type.REGULAR),
                "Test 1001 price is failed");

        //positive case
        assertDoesNotThrow(
                () -> cart.addItem("Item 100 price", 100, 1, Item.Type.REGULAR),
                "Test 100 price is failed");
    }

    @Test
    @DisplayName("Testing Item quantity")
    void testItemQuantity() {
        //negative cases
        assertThrows(Exception.class,
                () -> cart.addItem("Item 0 Quantity", 1, 0, Item.Type.REGULAR),
                "Test 0 quantity item is failed");
        assertThrows(Exception.class,
                () -> cart.addItem("Item 999 Quantity", 1, 1001, Item.Type.REGULAR),
                "Test 1001 quantity item is failed");

        //positive case
        assertDoesNotThrow(() -> cart.addItem("Item 1 Quantity", 1, 1, Item.Type.REGULAR),
                "Test 1 quantity item is failed");
    }

    @Test
    @DisplayName("Testing Item type")
    void testItemType() {
        //impossible to set item type not from Item.Type because it's enum
    }

    @Test
    @DisplayName("Testing more than 100 Items in the Shopping card")
    void testAddHundredItems() {
        //adding 98 items
        for (int i = 1; i < 99; i++) {
            hundredItemsCart.addItem("Item " + i, 1, 1, Item.Type.REGULAR);
        }

        //adding 99th item
        assertDoesNotThrow(() -> hundredItemsCart.addItem("Item 99", 1, 1, Item.Type.REGULAR),
                "Test adding of 99 items is failed");

        //adding 100th item
        assertThrows(IndexOutOfBoundsException.class,
                () -> hundredItemsCart.addItem("Item 100", 1, 1, Item.Type.REGULAR),
                "Test adding of 100 items is failed");
    }
}

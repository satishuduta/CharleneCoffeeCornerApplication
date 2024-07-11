package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class ReceiptTest {

    @Test
    public void testGenerateReceipt() {
        CharleneCoffeeCornerApplication.Receipt receipt = new CharleneCoffeeCornerApplication().new Receipt();

        List<String> orders = Arrays.asList(
                "large coffee with extra milk",
                "small coffee with special roast",
                "bacon roll",
                "orange juice"
        );

        String expectedReceipt = "large coffee with extra milk: 3.8 CHF\n" +
                "small coffee with special roast: 2.5 CHF\n" +
                "bacon roll: 4.5 CHF\n" +
                "orange juice: 3.95 CHF\n" +
                "Total: 14.75 CHF";

        String actualReceipt = receipt.generateReceipt(orders);

        assertEquals(expectedReceipt, actualReceipt);
    }

    @Test
    public void testFreeExtraWithSnack() {
        CharleneCoffeeCornerApplication.Receipt receipt = new CharleneCoffeeCornerApplication().new Receipt();

        List<String> orders = Arrays.asList(
                "large coffee with extra milk",
                "bacon roll"
        );

        String expectedReceipt = "large coffee with extra milk: 3.8 CHF\n" +
                "bacon roll: 4.5 CHF\n" +
                "Total: 8.3 CHF";

        String actualReceipt = receipt.generateReceipt(orders);

        assertEquals(expectedReceipt, actualReceipt);
    }

    @Test
    public void testFreeBeverage() {
        CharleneCoffeeCornerApplication.Receipt receipt = new CharleneCoffeeCornerApplication().new Receipt();
        List<String> orders = Arrays.asList(
                "small coffee",
                "small coffee",
                "small coffee",
                "small coffee",
                "small coffee"
        );
        String expected = "small coffee: 2.5 CHF\n" +
                "small coffee: 2.5 CHF\n" +
                "small coffee: 2.5 CHF\n" +
                "small coffee: 2.5 CHF\n" +
                "small coffee: FREE\n" +
                "Total: 10.0 CHF";
        assertEquals(expected, receipt.generateReceipt(orders));
    }

}

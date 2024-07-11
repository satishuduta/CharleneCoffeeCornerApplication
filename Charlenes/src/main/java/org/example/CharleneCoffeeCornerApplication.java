package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;

@SpringBootApplication
public class CharleneCoffeeCornerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CharleneCoffeeCornerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        List<String> orders = Arrays.asList(
                "large coffee with extra milk",
                "small coffee with special roast",
                "bacon roll",
                "orange juice"
        );
        Receipt receipt = new Receipt();
        System.out.println(receipt.generateReceipt(orders));
    }

    class Receipt {
        private final Map<String, Double> prices;
        private int beverageCount = 0;

        public Receipt() {
            prices = new HashMap<>();
            prices.put("small coffee", 2.50);
            prices.put("medium coffee", 3.00);
            prices.put("large coffee", 3.50);
            prices.put("bacon roll", 4.50);
            prices.put("orange juice", 3.95);
            prices.put("extra milk", 0.30);
            prices.put("foamed milk", 0.50);
            prices.put("special roast coffee", 0.90);
        }

        public String generateReceipt(List<String> orders) {
            StringBuilder receipt = new StringBuilder();
            double total = 0;
            boolean snackOrdered = false;

            for (String order : orders) {
                double price = 0;
                boolean hasExtra = false;
                if (order.contains("coffee")) {
                    beverageCount++;
                    if (beverageCount % 5 == 0) {
                        receipt.append(order).append(": FREE\n");
                        continue;
                    }
                }

                for (String item : prices.keySet()) {
                    if (order.contains(item)) {
                        price += prices.get(item);
                        if (item.equals("extra milk") || item.equals("foamed milk") || item.equals("special roast coffee")) {
                            hasExtra = true;
                        }
                    }
                }

                if (order.contains("bacon roll") || order.contains("orange juice")) {
                    snackOrdered = true;
                }

                if (snackOrdered && hasExtra) {
                    price -= getExtraPrice(order);
                    snackOrdered = false;
                }

                total += price;
                receipt.append(order).append(": ").append(price).append(" CHF\n");
            }

            receipt.append("Total: ").append(total).append(" CHF");
            return receipt.toString();
        }

        private double getExtraPrice(String order) {
            if (order.contains("extra milk")) return prices.get("extra milk");
            if (order.contains("foamed milk")) return prices.get("foamed milk");
            if (order.contains("special roast coffee")) return prices.get("special roast coffee");
            return 0;
        }
    }
}

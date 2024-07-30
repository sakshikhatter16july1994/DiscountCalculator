package com.discount.management.DiscountCalculator.Services;

import com.discount.management.DiscountCalculator.Entity.Bill;
import com.discount.management.DiscountCalculator.Entity.Item;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    public double applyDiscount(Bill bill) {
        double bulkDiscount = 0.0;
        double total = bill.calculateTotal();
        double percentageDiscount = bill.getUser().getDiscount();
        double nonGroceryTotal = bill.getItems().stream()
                .filter(item -> !item.isGrocery())
                .mapToDouble(Item::getPrice)
                .sum();

        double discount = nonGroceryTotal * percentageDiscount;
        if(percentageDiscount == 0.0){
             bulkDiscount = (int) (total / 100) * 5;
        }
        return total - discount - bulkDiscount;
    }
}

package com.discount.management.DiscountCalculator.Entity;

import com.discount.management.DiscountCalculator.Enum.ItemCategory;

public class Item {
    private Long id;
    private String name;
    private double price;
    private ItemCategory category;
    public Item(){

    }
    public Item(Long id, String name, double price, ItemCategory category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

     public boolean isGrocery() {
        return category == ItemCategory.GROCERIES;
    }
}

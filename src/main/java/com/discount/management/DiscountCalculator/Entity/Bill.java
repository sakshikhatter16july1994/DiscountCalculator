package com.discount.management.DiscountCalculator.Entity;

import java.util.List;


public class Bill {
    private Long id;
    private User user;
    private List<Item> items;

    public Bill() {
    }

    public Bill(Long id, User user, List<Item> items) {
        this.id = id;
        this.user = user;
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public List<Item> getItems() {
        return items;
    }


   public double calculateTotal() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }
}


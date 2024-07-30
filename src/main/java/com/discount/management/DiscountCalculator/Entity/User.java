package com.discount.management.DiscountCalculator.Entity;
import com.discount.management.DiscountCalculator.Enum.UserType;

import java.time.LocalDate;

public class User {
    private Long id;
    private String name;
    private UserType userType;
    private LocalDate joiningDate;


    public double getDiscount() {
        switch (userType) {
            case EMPLOYEE:
                return 0.30;
            case AFFILIATE:
                return 0.10;
            case CUSTOMER:
                if (LocalDate.now().minusYears(2).isAfter(joiningDate)) {
                    return 0.05;
                }
                break;
            default:
                return 0.0;
        }
        return 0.0;
    }

      public User() {

    }
}

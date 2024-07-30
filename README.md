# Discount Calculator
- Java 17
- Spring Boot 3.3.2
- 
*************************************************Enums*************************************************
  
UserType : Enum represent different types of users (EMPLOYEE, AFFILIATE, CUSTOMER, OTHER)
ItemCategory : Enum represent different categories of items (GROCERIES, OTHER)

*************************************************Classes************************************************* :

****User class**** : Represents a user of the retail website(id, name, userType, and joiningDate) . ***getDiscount**** method determines the discount percentage based on the user's type like employee,customer etc and duration as a customer if more than 2 years case.
****Bill class**** : Represents a bill  which contains a list of items and the user who owns the bill. The ***calculateTotal*** method sums up the prices of all items in the bill.(total amount)
****Item class**** : Represents an item in the bill(id, name, price, and category).The ***isGrocery*** method checks if the item falls under the groceries category.

*************************************************LOGIC Class************************************************* :

****DiscountService class**** : Contains the logic to apply discounts to a bill. The ***applyDiscount*** method calculates the net payable amount by applying the highest applicable percentage discount and additional bulk discount based on the total bill amount.
1. Calculate Total (Item 1 : 100  ,Item 2 : 200  , Total =300) non groceryitem
            double total = bill.calculateTotal();

2. Percentage Discount on basis of user (User is EMPLOYEE means 30 percentage discount on total amount 300)
           double percentageDiscount = bill.getUser().getDiscount();

3. Using java 8 ,filter out not grocery amount and find nonGroceryTotal
 double nonGroceryTotal = bill.getItems().stream()
                .filter(item -> !item.isGrocery())
                .mapToDouble(Item::getPrice)
                .sum();
4. Find discount(30 % of 300 = 90)
 double discount = nonGroceryTotal * percentageDiscount;

5. return amount after dedcuting discount from original amount (300 - 90 = 210)
   
6.  If there is any case in which discount is not applied ,them bulk discount is calculated(e.g. for $ 990, you get $ 45 as a discount).
   if(percentageDiscount == 0.0){
             bulkDiscount = (int) (total / 100) * 5;
        }

*************************************************Unit Tests*************************************************
The DiscountCalculatorApplicationTests class contains unit tests for the DiscountService class, covering all scenarios to ensure the correct application of discounts. 
The tests use Mockito for mocking dependencies and JUnit for assertions.
1. testApplyDiscount_employee = 30% discount
2. testApplyDiscount_affiliate = 10% discount
3. testApplyDiscount_customerOverTwoYears =5% discount.
4. testApplyDiscount_noDiscount = noDiscount
5. testApplyDiscount_groceries = noDiscount
6. testApplyBulkDiscount (e.g. for $ 990, you get $ 45 as a discount).

***********Coverage REPORT Attached***********
   
***********High Level Diagram Attached***********

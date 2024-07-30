package com.discount.management.DiscountCalculator;

import com.discount.management.DiscountCalculator.Entity.Bill;
import com.discount.management.DiscountCalculator.Entity.Item;
import com.discount.management.DiscountCalculator.Enum.ItemCategory;
import com.discount.management.DiscountCalculator.Entity.User;
import com.discount.management.DiscountCalculator.Services.DiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
public  class DiscountCalculatorApplicationTests {

	private DiscountService discountService;

	@Mock
	private User user;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		discountService = new DiscountService();
	}

	@Test
	public void testApplyDiscount_employee() {
		when(user.getDiscount()).thenReturn(0.30);

		Item item1 = new Item(1L, "item1", 100, ItemCategory.OTHER);
		Item item2 = new Item(2L, "item2", 200, ItemCategory.OTHER);
		Bill bill = new Bill(1L, user, Arrays.asList(item1, item2));

		double finalAmount = discountService.applyDiscount(bill);
		assertEquals(210, finalAmount, 0.01);
	}

	@Test
	public void testApplyDiscount_affiliate() {
		when(user.getDiscount()).thenReturn(0.10);

		Item item1 = new Item(1L, "item1", 100, ItemCategory.OTHER);
		Item item2 = new Item(2L, "item2", 200, ItemCategory.OTHER);
		Bill bill = new Bill(1L, user, Arrays.asList(item1, item2));

		double finalAmount = discountService.applyDiscount(bill);
		assertEquals(270, finalAmount, 0.01);
	}

	@Test
	public void testApplyDiscount_customerOverTwoYears() {
		when(user.getDiscount()).thenReturn(0.05);

		Item item1 = new Item(1L, "item1", 100, ItemCategory.OTHER);
		Item item2 = new Item(2L, "item2", 200, ItemCategory.OTHER);
		Bill bill = new Bill(1L, user, Arrays.asList(item1, item2));

		double finalAmount = discountService.applyDiscount(bill);
		assertEquals(285, finalAmount, 0.01);
	}

	@Test
	public void testApplyDiscount_noDiscount() {
		when(user.getDiscount()).thenReturn(0.0);

		Item item1 = new Item(1L, "item1", 100, ItemCategory.OTHER);
		Item item2 = new Item(2L, "item2", 200, ItemCategory.OTHER);
		Bill bill = new Bill(1L, user, Arrays.asList(item1, item2));

		double finalAmount = discountService.applyDiscount(bill);
		assertEquals(285, finalAmount, 0.01);
	}

	@Test
	public void testApplyDiscount_groceries() {
		when(user.getDiscount()).thenReturn(0.30);

		Item item1 = new Item(1L, "item1", 100, ItemCategory.GROCERIES);
		Item item2 = new Item(2L, "item2", 200, ItemCategory.OTHER);
		Bill bill = new Bill(1L, user, Arrays.asList(item1, item2));

		double finalAmount = discountService.applyDiscount(bill);
		assertEquals(240, finalAmount, 0.01);
	}

	@Test
	public void testApplyBulkDiscount() {
		when(user.getDiscount()).thenReturn(0.0);

		Item item1 = new Item(1L, "item1", 500, ItemCategory.OTHER);
		Item item2 = new Item(2L, "item2", 600, ItemCategory.OTHER);
		Bill bill = new Bill(1L, user, Arrays.asList(item1, item2));

		double finalAmount = discountService.applyDiscount(bill);
		assertEquals(1045, finalAmount, 0.01);
	}
}

package com.discount.management.DiscountCalculator.Controller;

import com.discount.management.DiscountCalculator.Services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/discount")
public class DiscountController {

    @Autowired
    DiscountService service;

 }

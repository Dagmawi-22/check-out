package com.dagmawi.check_out.checkout.service;

import com.dagmawi.check_out.checkout.model.Checkout;
import com.dagmawi.check_out.checkout.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {

    @Autowired
    private CheckoutRepository checkoutRepository;

    public Checkout savePendingCheckout(String name, String email, double amount) {
        Checkout checkout = new Checkout();
        checkout.setName(name);
        checkout.setEmail(email);
        checkout.setAmount(amount);
        return checkoutRepository.save(checkout);
    }
}

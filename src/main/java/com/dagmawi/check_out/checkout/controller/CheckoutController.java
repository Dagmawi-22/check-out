package com.dagmawi.check_out.checkout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import com.dagmawi.check_out.checkout.model.Checkout;
import com.dagmawi.check_out.checkout.service.CheckoutService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/v1/payment")
public class CheckoutController {

    private CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @Autowired

    @GetMapping("/initiate")
    public ResponseEntity<String> initiatePayment() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("html/checkout.html");
        String html = StreamUtils.copyToString(htmlFile.getInputStream(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(html);
    }
 
    @PostMapping(value = "/checkout", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> handleCheckout(@RequestParam String name,
            @RequestParam String email,
                                                 @RequestParam double amount) {
        if (amount < 1 || amount > 1000) {
            return ResponseEntity.badRequest().body("Invalid amount.");
        }

        Checkout savedCheckout = checkoutService.savePendingCheckout(name, email, amount);

        String responseHtml = "<html><body><h2>Payment Request Saved</h2>"
                + "<p>Name: " + savedCheckout.getName() + "</p>"
                + "<p>Amount: $" + savedCheckout.getAmount() + "</p>"
                + "<p>Status: " + savedCheckout.getStatus() + "</p>"
                + "<p>Reference ID: " + savedCheckout.getId() + "</p>"
                + "<a href='/api/v1/payment/initiate'>Back to Checkout</a>"
                + "</body></html>";

        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(responseHtml);
    }


}

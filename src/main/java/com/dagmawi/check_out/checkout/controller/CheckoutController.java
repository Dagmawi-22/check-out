package com.dagmawi.check_out.checkout.controller;

import com.dagmawi.check_out.checkout.service.CheckoutService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/v1/payment")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @GetMapping("/initiate")
    public ResponseEntity<String> initiatePayment() throws IOException {
        return serveHtmlFile("html/checkout.html");
    }

    @PostMapping(value = "/checkout", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> handleCheckout(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam double amount) throws IOException {

        if (amount < 1 || amount > 1000) {
            return ResponseEntity.badRequest().body("Invalid amount.");
        }

        checkoutService.savePendingCheckout(name, email, amount);
        return serveHtmlFile("html/success.html");
    }

    private ResponseEntity<String> serveHtmlFile(String path) throws IOException {
        ClassPathResource htmlFile = new ClassPathResource(path);
        String html = StreamUtils.copyToString(htmlFile.getInputStream(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(html);
    }
}

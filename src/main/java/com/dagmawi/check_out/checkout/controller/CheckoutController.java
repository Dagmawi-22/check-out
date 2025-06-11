package com.dagmawi.check_out.checkout.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/v1/payment")
public class CheckoutController {

    @Autowired

    @GetMapping("/initiate")
    public ResponseEntity<String> initiatePayment() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("html/checkout.html");
        String html = StreamUtils.copyToString(htmlFile.getInputStream(), StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(html);
    }

}

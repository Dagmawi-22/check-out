package com.daggy.email_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("hello form email service");
    }
}

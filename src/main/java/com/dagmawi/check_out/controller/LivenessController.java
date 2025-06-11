package com.dagmawi.check_out.controller;

import com.dagmawi.check_out.service.LivenessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/liveness")
public class LivenessController {

    private final LivenessService livenessService;

    @Autowired
    public LivenessController(LivenessService livenessService) {
        this.livenessService = livenessService;
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> livenessCheck() {
        Map<String, Object> response = Map.of(
            "status", livenessService.getStatus(),
            "message", livenessService.getMessage()
        );

        return ResponseEntity
            .status(livenessService.isAlive() ? HttpStatus.OK : HttpStatus.SERVICE_UNAVAILABLE)
            .body(response);
    }
    
    @GetMapping("/db")
    public ResponseEntity<String> checkDatabase() {
        if (livenessService.isDatabaseConnected()) {
            return ResponseEntity.ok("Database is connected");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database is down");
        }
    }
}

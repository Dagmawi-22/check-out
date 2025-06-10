package com.dagmawi.check_out.controller;

import com.dagmawi.check_out.service.LivenessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class LivenessController {

    private final LivenessService livenessService;

    @Autowired
    public LivenessController(LivenessService livenessService) {
        this.livenessService = livenessService;
    }

    @GetMapping("/liveness")
    public ResponseEntity<Map<String, Object>> livenessCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", livenessService.getStatus());
        response.put("message", livenessService.getMessage());

        if (livenessService.isAlive()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
    }
}

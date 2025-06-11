package com.dagmawi.check_out.liveness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.dagmawi.check_out.liveness.service.LivenessService;

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
    public ResponseEntity<Map<String, Object>> checkDatabase() {
         Map<String, Object> response = Map.of(
            "status", livenessService.isDatabaseConnected(),
            "message", livenessService.getDbStatusMessage()
        );
       return ResponseEntity
            .status(livenessService.isDatabaseConnected() ? HttpStatus.OK : HttpStatus.SERVICE_UNAVAILABLE)
            .body(response);
    }
}

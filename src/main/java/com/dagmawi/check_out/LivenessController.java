package com.dagmawi.check_out;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LivenessController {

    @GetMapping("/liveness")
    public String livenessCheck() {
        return "I'm alive!";
    }
}

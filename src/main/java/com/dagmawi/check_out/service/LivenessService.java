
package com.dagmawi.check_out.service;

import org.springframework.stereotype.Service;

@Service
public class LivenessService {
    private boolean isAlive = true;

    public boolean isAlive() {
        return isAlive;
    }

    public String getStatus() {
        return isAlive ? "UP" : "DOWN";
    }

    public String getMessage() {
        return isAlive ? "I'm alive!" : "Service is unavailable";
    }
}

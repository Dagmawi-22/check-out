
package com.dagmawi.check_out.liveness.service;

import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class LivenessService {
    
    @Autowired
    private DataSource dataSource;
    
    public boolean isDatabaseConnected() {
        try (Connection connection = dataSource.getConnection()) {
            return !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
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
    public String getDbStatusMessage() {
        return isDatabaseConnected() ? "Database connected!" : "Failed to connect to db.";
    }
}

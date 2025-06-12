package com.dagmawi.check_out.checkout.repository;

import com.dagmawi.check_out.checkout.model.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

    List<Checkout> findByEmail(String email);
    
    List<Checkout> findByAmountGreaterThan(double amount);
    
    List<Checkout> findByAmountBetween(double minAmount, double maxAmount);
    
    List<Checkout> findByNameContainingIgnoreCase(String name);
    
    Optional<Checkout> findOneByEmail(String email);
}
package com.dagmawi.check_out.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dagmawi.check_out.model.Checkout;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
}

package com.dagmawi.check_out.checkout.repository;

import com.dagmawi.check_out.checkout.model.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
}

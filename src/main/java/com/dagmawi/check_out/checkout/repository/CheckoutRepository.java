package com.dagmawi.check_out.checkout.repository;

import org.springframework.stereotype.Repository;

import com.dagmawi.check_out.checkout.model.Checkout;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {
}

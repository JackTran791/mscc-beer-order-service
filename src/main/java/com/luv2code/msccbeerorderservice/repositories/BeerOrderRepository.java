package com.luv2code.msccbeerorderservice.repositories;

import com.luv2code.msccbeerorderservice.domain.BeerOrder;
import com.luv2code.msccbeerorderservice.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Jack Tran
 */
public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {
    Page<BeerOrder> findAllByCustomer(Customer customer, Pageable pageable);
}

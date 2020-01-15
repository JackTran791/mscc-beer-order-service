package com.luv2code.msccbeerorderservice.repositories;

import com.luv2code.msccbeerorderservice.domain.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Jack Tran
 */
public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {
}

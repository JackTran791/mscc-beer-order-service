package com.luv2code.msccbeerorderservice.services;

import com.luv2code.msccbeerorderservice.web.model.BeerOrderDto;
import com.luv2code.msccbeerorderservice.web.model.BeerOrderPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * @author Jack Tran
 */
public interface BeerOrderService {
    BeerOrderPagedList listOrders(UUID customerId, Pageable pageable);

    BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto);

    BeerOrderDto getOrderById(UUID customerId, UUID orderId);

    void pickupOrder(UUID customerId, UUID orderId);
}

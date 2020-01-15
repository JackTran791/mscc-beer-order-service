package com.luv2code.msccbeerorderservice.services;

import com.luv2code.msccbeerorderservice.domain.BeerOrder;
import com.luv2code.msccbeerorderservice.domain.Customer;
import com.luv2code.msccbeerorderservice.domain.OrderStatusEnum;
import com.luv2code.msccbeerorderservice.repositories.BeerOrderRepository;
import com.luv2code.msccbeerorderservice.repositories.CustomerRepository;
import com.luv2code.msccbeerorderservice.web.mappers.BeerOrderMapper;
import com.luv2code.msccbeerorderservice.web.model.BeerOrderDto;
import com.luv2code.msccbeerorderservice.web.model.BeerOrderPagedList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Jack Tran
 */
@Slf4j
@Service
public class BeerOrderServiceImpl implements BeerOrderService {

    private final CustomerRepository customerRepository;
    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;

    public BeerOrderServiceImpl(CustomerRepository customerRepository, BeerOrderRepository beerOrderRepository, BeerOrderMapper beerOrderMapper) {
        this.customerRepository = customerRepository;
        this.beerOrderRepository = beerOrderRepository;
        this.beerOrderMapper = beerOrderMapper;
    }

    @Override
    public BeerOrderPagedList listOrders(UUID customerId, Pageable pageable) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            Page<BeerOrder> beerOrderPage =
                    beerOrderRepository.findAllByCustomer(customerOptional.get(), pageable);

            return new BeerOrderPagedList(beerOrderPage
                    .stream()
                    .map(beerOrderMapper::beerOrderToDto)
                    .collect(Collectors.toList()), PageRequest.of(
                    beerOrderPage.getPageable().getPageNumber(),
                    beerOrderPage.getPageable().getPageSize()),
                    beerOrderPage.getTotalElements());
        } else {
            return null;
        }
    }

    @Override
    public BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto) {

        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if(customerOptional.isPresent()) {
            BeerOrder beerOrder = beerOrderMapper.dtoToBeerOrder(beerOrderDto);
            beerOrder.setId(null);
            beerOrder.setCustomer(customerOptional.get());
            beerOrder.setOrderStatus(OrderStatusEnum.NEW);

            beerOrder.getBeerOrderLines().forEach(line -> line.setBeerOrder(beerOrder));

            BeerOrder savedBeerOrder = beerOrderRepository.saveAndFlush(beerOrder);

            log.debug("Saved Beer Order: " + beerOrder.getId());

            return beerOrderMapper.beerOrderToDto(savedBeerOrder);
        }
        throw new RuntimeException("Customer Not Found");
    }

    @Override
    public BeerOrderDto getOrderById(UUID customerId, UUID orderId) {
        return beerOrderMapper.beerOrderToDto(getOrder(customerId, orderId));
    }

    @Override
    public void pickupOrder(UUID customerId, UUID orderId) {
        BeerOrder beerOrder = getOrder(customerId, orderId);
        beerOrder.setOrderStatus(OrderStatusEnum.PICKED_UP);

        beerOrderRepository.save(beerOrder);
    }

    private BeerOrder getOrder(UUID customerId, UUID orderId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if(customerOptional.isPresent()) {
            Optional<BeerOrder> beerOrderOptional = beerOrderRepository.findById(orderId);

            if(beerOrderOptional.isPresent()) {
                BeerOrder beerOrder = beerOrderOptional.get();

                if(beerOrder.getCustomer().getId().equals(customerId)) {
                    return  beerOrder;
                }
            }
            throw new RuntimeException("Beer Order Not Found");
        }

        throw new RuntimeException("Customer Not Found");
    }

}

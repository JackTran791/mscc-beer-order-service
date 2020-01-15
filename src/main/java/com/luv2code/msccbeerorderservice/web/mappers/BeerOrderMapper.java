package com.luv2code.msccbeerorderservice.web.mappers;

import com.luv2code.msccbeerorderservice.domain.BeerOrder;
import com.luv2code.msccbeerorderservice.web.model.BeerOrderDto;
import org.mapstruct.Mapper;

/**
 * @author Jack Tran
 */
@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {
    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);

    BeerOrder dtoToBeerOrder(BeerOrderDto beerOrderDto);
}

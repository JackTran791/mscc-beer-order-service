package com.luv2code.msccbeerorderservice.web.mappers;

import com.luv2code.msccbeerorderservice.domain.BeerOrderLine;
import com.luv2code.msccbeerorderservice.web.model.BeerOrderLineDto;
import org.mapstruct.Mapper;

/**
 * @author Jack Tran
 */
@Mapper(uses = {DateMapper.class})
public interface BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}

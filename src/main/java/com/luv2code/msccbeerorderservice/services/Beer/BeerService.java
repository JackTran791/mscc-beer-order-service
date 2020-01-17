package com.luv2code.msccbeerorderservice.services.Beer;

import com.luv2code.msccbeerorderservice.web.model.BeerDto;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Jack Tran
 */
public interface BeerService {

    Optional<BeerDto> getBeerById(UUID uuid);

    Optional<BeerDto> getBeerByUpc(String upc);
}

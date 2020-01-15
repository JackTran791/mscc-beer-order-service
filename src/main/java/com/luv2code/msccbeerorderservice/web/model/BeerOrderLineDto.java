package com.luv2code.msccbeerorderservice.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @author Jack Tran
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderLineDto extends BaseItem{

    @Builder
    public BeerOrderLineDto(UUID uuid, Integer version, OffsetDateTime createdDate,
                            OffsetDateTime lastModifiedDate, String upc, String beerName,
                            UUID beerId, Integer orderQuantity) {
        super(uuid, version, createdDate, lastModifiedDate);
        this.upc = upc;
        this.beerName = beerName;
        this.beerId = beerId;
        this.orderQuantity = orderQuantity;
    }

    private String upc;
    private String beerName;
    private UUID beerId;
    private Integer orderQuantity = 0;
}

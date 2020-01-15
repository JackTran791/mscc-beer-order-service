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
public class OrderStatusUpdate extends BaseItem {

    @Builder
    public OrderStatusUpdate(UUID uuid, Integer version, OffsetDateTime createdDate,
                             OffsetDateTime lastModifiedDate, UUID orderId, String customerRef, String orderStatus) {
        super(uuid, version, createdDate, lastModifiedDate);
        this.orderId = orderId;
        this.customerRef = customerRef;
        this.orderStatus = orderStatus;
    }

    private UUID orderId;
    private String customerRef;
    private String orderStatus;
}

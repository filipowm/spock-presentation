package com.filipowm.spock.payment.model;

import lombok.Getter;

import java.util.UUID;

/**
 * @author Mateusz Filipowicz
 */
@Getter
public class PaymentResult {

    private String id;
    private PaymentStatus status;

    public PaymentResult(PaymentStatus status) {
        this.id = UUID.randomUUID().toString();
        this.status = status;
    }
}

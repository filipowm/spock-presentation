package com.filipowm.spock.order.model;

/**
 * @author Mateusz Filipowicz
 */
public enum OrderStatus {

    NEW,
    UNDER_PREPARATION,
    PREPARED,
    PAID,
    SENT,
    PAYMENT_ERROR,
    CLOSED;

}

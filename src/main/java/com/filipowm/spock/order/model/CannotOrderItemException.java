package com.filipowm.spock.order.model;

/**
 * @author Mateusz Filipowicz
 */
public class CannotOrderItemException extends Exception {

    public CannotOrderItemException(String message) {
        super(message);
    }

}

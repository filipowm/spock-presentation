package com.filipowm.spock.payment.infrastructure;

import com.filipowm.spock.payment.model.PaymentResult;

/**
 * @author Mateusz Filipowicz
 */
public interface PaymentProvider {

    PaymentResult pay(float money, String accountTo);
    void shutdown();

}

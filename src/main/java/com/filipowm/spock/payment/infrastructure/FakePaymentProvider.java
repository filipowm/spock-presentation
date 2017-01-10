package com.filipowm.spock.payment.infrastructure;

import com.filipowm.spock.payment.model.PaymentResult;
import com.filipowm.spock.payment.model.PaymentStatus;
import com.filipowm.spock.utils.Sleeper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Mateusz Filipowicz
 */
@Slf4j
public class FakePaymentProvider implements PaymentProvider {

    @Override
    public PaymentResult pay(float money, String accountTo) {
        log.debug("Transferring {} to {}", money, accountTo);
        Sleeper.sleep(500, 3000);
        return new PaymentResult(PaymentStatus.OK);
    }
}

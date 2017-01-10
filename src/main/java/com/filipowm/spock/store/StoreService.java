package com.filipowm.spock.store;

import com.filipowm.spock.order.model.Order;
import com.filipowm.spock.utils.Sleeper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Mateusz Filipowicz
 */
@Slf4j
public class StoreService {

    public void prepare(Order order) {
        log.debug("Preparing order with id={}", order.getId());
        Sleeper.sleep(1000, 3000);
        log.info("Order with id={} prepared and ready to be sent", order.getId());
    }

}

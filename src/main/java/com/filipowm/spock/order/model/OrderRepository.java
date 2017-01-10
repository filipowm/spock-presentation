package com.filipowm.spock.order.model;

import com.filipowm.spock.Repository;

/**
 * @author Mateusz Filipowicz
 */
public interface OrderRepository extends Repository<Order> {

    Order updateStatus(Order order, OrderStatus status);

}

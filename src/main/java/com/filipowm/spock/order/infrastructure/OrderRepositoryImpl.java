package com.filipowm.spock.order.infrastructure;

import com.filipowm.spock.FakeRepository;
import com.filipowm.spock.order.model.Order;
import com.filipowm.spock.order.model.OrderRepository;
import com.filipowm.spock.order.model.OrderStatus;

/**
 * @author Mateusz Filipowicz
 */
public class OrderRepositoryImpl extends FakeRepository<Order> implements OrderRepository {

    @Override
    public Order updateStatus(Order order, OrderStatus status) {
        order.setStatus(status);
        return save(order);
    }
}

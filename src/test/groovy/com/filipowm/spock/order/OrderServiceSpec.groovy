package com.filipowm.spock.order

import static com.filipowm.spock.order.model.OrderStatus.*
import static com.filipowm.spock.payment.model.PaymentStatus.*

import com.filipowm.spock.item.model.Item
import com.filipowm.spock.item.model.ItemService
import com.filipowm.spock.order.infrastructure.OrderRepositoryImpl
import com.filipowm.spock.order.model.Order
import com.filipowm.spock.order.model.OrderService
import com.filipowm.spock.payment.infrastructure.PaymentProvider
import com.filipowm.spock.payment.model.PaymentResult
import com.filipowm.spock.store.StoreService
import com.filipowm.spock.user.model.User
import spock.lang.Specification
import spock.lang.Unroll
import spock.util.concurrent.PollingConditions

/**
 * @author Mateusz Filipowicz
 */
class OrderServiceSpec extends Specification {

    def storeService = new StoreService()
    def orderRepository = new OrderRepositoryImpl()
    def itemService = Mock(ItemService)
    def paymentProvider = Mock(PaymentProvider)

    def orderService = new OrderService(storeService, orderRepository, itemService, paymentProvider)

    void 'payment ends with unknown error'() {
        given:
        def order = new Order(Mock(User), Mock(List), 0, NEW)

        when:
        def status = orderService.pay(order)

        then:
        1 * paymentProvider.pay(_, _) >> { throw new IllegalArgumentException() }
        status == UNKNOWN_ERROR
    }

    @Unroll
    void 'for payment #paymentStatus order is #orderStatus'() {
        given:
        def order = new Order(Mock(User), Mock(List), 0, NEW)
        def result = Mock(PaymentResult)

        when:
        def status = orderService.pay(order)

        then:
        1 * paymentProvider.pay(_, _) >> result
        (1..2) * result.getStatus() >> paymentStatus

        status == paymentStatus

        where:
        paymentStatus            | orderStatus
        OK                       | PAID
        NO_MONEY                 | PAYMENT_ERROR
        BLOCKED_ACCOUNT          | PAYMENT_ERROR
        UNKNOWN_ERROR            | PAYMENT_ERROR
        INCORRECT_ACCOUNT_NUMBER | PAYMENT_ERROR
    }

    void 'order is placed properly'() {
        given:
        def items = [new Item('my item', 14.5, 5)]
        def polling = new PollingConditions(timeout: 3)

        when:
        def order = orderService.order(items, Mock(User))

        then:
        order.status == NEW

        polling.eventually {
            assert order.status == PREPARED
        }
    }

}
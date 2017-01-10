package com.filipowm.spock.order.model;

import com.filipowm.spock.item.model.Item;
import com.filipowm.spock.item.model.ItemService;
import com.filipowm.spock.payment.infrastructure.PaymentProvider;
import com.filipowm.spock.payment.model.PaymentResult;
import com.filipowm.spock.payment.model.PaymentStatus;
import com.filipowm.spock.store.StoreService;
import com.filipowm.spock.user.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * @author Mateusz Filipowicz
 */
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private static final String FAKE_ACCOUNT = "99 9999 9999 9999 9999";

    private final StoreService storeService;
    private final OrderRepository orderRepository;
    private final ItemService itemService;
    private final PaymentProvider paymentProvider;

    public PaymentStatus pay(Order order) {
        CompletableFuture<PaymentResult> future = CompletableFuture.supplyAsync(() -> paymentProvider.pay(order.getCost(), FAKE_ACCOUNT));
        //TODO beautiful code to refactor...

        PaymentResult result;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Unable to pay for order={}", order.getId());
            result = new PaymentResult(PaymentStatus.UNKNOWN_ERROR);
        }
        updateOrderStatusAfterPayment(order, result);
        return result.getStatus();
    }

    public boolean canOrder(Item item, User user, int quantity) {
        return item.getQuantity() >= quantity;
    }

    public Order order(List<Item> items, User user) throws CannotOrderItemException {
        if (items.isEmpty()) {
            throw new NullPointerException("No items ordered");
        }
        Map<Integer, List<Item>> grouped = groupItems(items);

        if (! canOrder(grouped, user)) {
            throw new CannotOrderItemException("business logic check failed");
        }
        Order order = Order.builder()
                .items(items)
                .user(user)
                .status(OrderStatus.NEW)
                .cost(sumCosts(items))
                .build();
        orderRepository.save(order);
        itemService.decrementQuantity(items);

        CompletableFuture.runAsync(() -> {
            storeService.prepare(order);
            orderRepository.updateStatus(order, OrderStatus.PREPARED);
        }, Executors.newSingleThreadExecutor());
        return order;
    }

    private float sumCosts(List<Item> items) {
        float cost = 0L;
        for (Item item : items) {
            cost += item.getPrice();
        }
        return cost;
    }

    private Map<Integer, List<Item>> groupItems(List<Item> items) {
        Map<Integer, List<Item>> groups = new HashMap<>();
        for (Item item : items) {
            List<Item> group = groups.get(item.getId());
            if (group == null) {
                group = new ArrayList<>();
                groups.put(item.getId(), group);
            }
            group.add(item);
        }
        return groups;
    }

    private boolean canOrder(Map<Integer, List<Item>> groups, User user) {
        boolean canOrder = true;
        for (List<Item> items : groups.values()) {
            int quantity = items.size();
            Item item = items.get(0);
            if (! canOrder(item, user, quantity)) {
                canOrder = false;
                log.warn("Cannot order item {}", item.getName());
            }
        }
        return canOrder;
    }

    private void updateOrderStatusAfterPayment(Order order, PaymentResult result) {
        OrderStatus status = OrderStatus.PAYMENT_ERROR;
        if (result.getStatus() == PaymentStatus.OK) {
            status = OrderStatus.PAID;
        }
        orderRepository.updateStatus(order, status);
    }

}

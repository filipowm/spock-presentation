package com.filipowm.spock.order.model;

import com.filipowm.spock.AbstractEntity;
import com.filipowm.spock.item.model.Item;
import com.filipowm.spock.user.model.User;
import lombok.*;

import java.util.List;

/**
 * @author Mateusz Filipowicz
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class Order extends AbstractEntity {

    private User user;
    private List<Item> items;
    private float cost;
    private OrderStatus status;

}

package com.filipowm.spock.order.model;

import com.filipowm.spock.AbstractEntity;
import com.filipowm.spock.item.model.Item;
import com.filipowm.spock.user.model.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Wither;

import java.util.List;

/**
 * @author Mateusz Filipowicz
 */
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
public class Order extends AbstractEntity {

    private final User user;
    private final List<Item> items;
    private final float cost;
    private final @Wither OrderStatus status;

}

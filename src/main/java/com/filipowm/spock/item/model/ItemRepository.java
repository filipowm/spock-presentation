package com.filipowm.spock.item.model;

import com.filipowm.spock.Repository;

/**
 * @author Mateusz Filipowicz
 */
public interface ItemRepository extends Repository<Item> {

    Item findByName(String name);

}

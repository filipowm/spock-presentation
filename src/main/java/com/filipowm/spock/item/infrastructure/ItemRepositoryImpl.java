package com.filipowm.spock.item.infrastructure;

import com.filipowm.spock.FakeRepository;
import com.filipowm.spock.item.model.Item;
import com.filipowm.spock.item.model.ItemRepository;

/**
 * @author Mateusz Filipowicz
 */
public class ItemRepositoryImpl extends FakeRepository<Item> implements ItemRepository {

    @Override
    public Item findByName(String name) {
        return data.values()
                .stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .get();
    }
}

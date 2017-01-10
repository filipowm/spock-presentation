package com.filipowm.spock.item.infrastructure;

import com.filipowm.spock.FakeRepository;
import com.filipowm.spock.HellException;
import com.filipowm.spock.item.model.Item;
import com.filipowm.spock.item.model.ItemRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Mateusz Filipowicz
 */
@Slf4j
public class ItemRepositoryImpl extends FakeRepository<Item> implements ItemRepository {

    @Override
    public Item findByName(String name) {
        return data.values()
                .stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Item save(Item object) {
        if (object.getPrice() <= 0) {
            log.debug("Price of item ({}) is 0 or less ", object);
            throw new HellException("Price cannot be 0 or less");
        }
        return super.save(object);
    }
}

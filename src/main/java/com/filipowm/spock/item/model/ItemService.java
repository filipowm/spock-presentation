package com.filipowm.spock.item.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Mateusz Filipowicz
 */
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    public int updateQuantity(Integer id, int quantity) {
        Item item = itemRepository.findById(id);
        return updateQuantity(item, quantity);
    }

    public int updateQuantity(Item item, int quantity) {
        int quantityToSet = quantity;
        if (quantity < 0) {
            quantityToSet = 0;
        }
        item.setQuantity(quantityToSet);
        itemRepository.save(item);
        return quantityToSet;
    }

    public void decrementQuantity(List<Item> items) {
        items.forEach(item -> updateQuantity(item, item.getQuantity() - 1));
    }

}

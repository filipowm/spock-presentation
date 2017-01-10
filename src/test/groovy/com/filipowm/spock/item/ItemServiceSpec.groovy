package com.filipowm.spock.item

import com.filipowm.spock.item.model.Item
import com.filipowm.spock.item.model.ItemRepository
import com.filipowm.spock.item.model.ItemService
import spock.lang.Specification
import spock.lang.Unroll


/**
 * @author Mateusz Filipowicz
 */
class ItemServiceSpec extends Specification {

    @Unroll
    void 'quantity is updated to #quantity with result #expectedResult'() {
        given:
        def repository = Mock(ItemRepository)
        def service = new ItemService(repository)
        def item = new Item('test', 1, 100)

        when:
        int result = service.updateQuantity(item, quantity)

        then:
        1 * repository.save(item)
        item.quantity == result
        item.quantity == expectedResult


        where:
        quantity || expectedResult
        1        || 1
        0        || 0
        -1       || 0
    }

}
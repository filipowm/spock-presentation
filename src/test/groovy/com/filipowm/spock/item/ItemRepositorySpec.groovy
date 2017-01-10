package com.filipowm.spock.item

import com.filipowm.spock.HellException
import com.filipowm.spock.item.infrastructure.ItemRepositoryImpl
import com.filipowm.spock.item.model.Item
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll


/**
 * @author Mateusz Filipowicz
 */
class ItemRepositorySpec extends Specification {

    @Shared
    def repository = new ItemRepositoryImpl()

    void 'new item is saved'() {
        given:
        def item = new Item('test', 1.0, 1)

        when:
        def saved = repository.save(item)

        then:
        saved.id > 0

        when:
        def loaded = repository.findById(saved.id)

        then:
        loaded.equals(saved)
    }

    @Unroll
    void 'new item is not saved when price is #price'() {
        given:
        def repository = new ItemRepositoryImpl()
        def item = new Item('test', price, 1)

        when:
        repository.save(item)

        then:
        def exc = thrown(HellException)
        exc.message.contains 'Price'

        where:
        price << [0, -0.000001, -1, Integer.MIN_VALUE]
    }

    @Unroll
    void 'item with #name #label'() {
        expect:
        (repository.findByName(name) != null) == expectedResult

        where:
        name   | label        || expectedResult
        'test' | 'exists'     || true
        'none' | 'not exists' || false

    }
}
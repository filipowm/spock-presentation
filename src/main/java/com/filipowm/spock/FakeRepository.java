package com.filipowm.spock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mateusz Filipowicz
 */
public abstract class FakeRepository<T extends Entity> implements Repository<T> {

    protected final Map<Integer, T> data = new ConcurrentHashMap<>();
    private final AtomicInteger sequence = new AtomicInteger();

    @Override
    public T save(T object) {
        if (object.getId() == null) {
            int id = sequence.incrementAndGet();
            object.setId(id);
        }
        data.put(object.getId(), object);
        return object;
    }

    @Override
    public T findById(Integer id) {
        return data.get(id);
    }

}

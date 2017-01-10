package com.filipowm.spock;

/**
 * @author Mateusz Filipowicz
 */
public interface Repository<T extends Entity> {

    T save(T object);
    T findById(Integer id);

}

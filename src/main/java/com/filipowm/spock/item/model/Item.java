package com.filipowm.spock.item.model;

import com.filipowm.spock.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Mateusz Filipowicz
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Item extends AbstractEntity {

    private String name;
    private float price;
    private int quantity;

}

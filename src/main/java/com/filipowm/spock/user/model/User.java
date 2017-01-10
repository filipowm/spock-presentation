package com.filipowm.spock.user.model;

import com.filipowm.spock.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Mateusz Filipowicz
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {

    private final String name;

}

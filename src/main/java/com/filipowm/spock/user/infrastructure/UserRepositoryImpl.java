package com.filipowm.spock.user.infrastructure;

import com.filipowm.spock.FakeRepository;
import com.filipowm.spock.user.model.User;
import com.filipowm.spock.user.model.UserRepository;

/**
 * @author Mateusz Filipowicz
 */
public class UserRepositoryImpl extends FakeRepository<User> implements UserRepository {
}

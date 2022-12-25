package org.voting.repository;

import org.voting.entity.person.User;

import java.util.List;

public interface UserRepository {
    User get(int id);

    User save(User user);

    boolean delete(int id);

    User getByEmail(String email);

    List<User> getAll();
}

package org.voting.service;

import org.springframework.util.Assert;
import org.voting.entity.person.User;
import org.voting.repository.UserRepository;

import java.util.List;

import static org.voting.util.ValidationUtil.checkNotFound;
import static org.voting.util.ValidationUtil.checkNotFoundWithId;

public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User create(User user) {
        Assert.notNull(user, "user must be not null");
        return repository.save(user);
    }

    public User update(User user) {
        Assert.notNull(user, "user must be not null");
        return checkNotFoundWithId(repository.save(user), user.getId());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return repository.getAll();
    }
}

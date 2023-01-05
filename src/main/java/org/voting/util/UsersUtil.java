package org.voting.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.voting.entity.person.User;

public class UsersUtil {

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
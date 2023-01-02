package org.voting;

import org.springframework.security.core.GrantedAuthority;
import org.voting.entity.person.User;

import java.io.Serial;
import java.util.Collection;


public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    @Serial
    private static final long serialVersionUID = 1L;

    private User user;

    public AuthorizedUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

/*    public AuthorizedUser(User user) {
        super(user.getName(), user.getPassword(), user.getRoles());
        this.user = user;
    }*/

        public int getId() {
        return user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
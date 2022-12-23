package org.voting.entity.person;

import org.springframework.util.CollectionUtils;
import org.voting.entity.abstractEntity.AbstractBaseEntity;
import org.voting.entity.abstractEntity.AbstractNamedEntity;

import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

public class User extends AbstractNamedEntity {

    private String email;
    private String password;
    private boolean enabled = true;
    private Date registered = new Date();
    private Set<Role> roles;

    public String getEmail() {
        return email;
    }

    public User() {
    }


    public User(Integer id, String name, String email, String password,
                boolean enabled, Date registered, Collection<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
    }


    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}

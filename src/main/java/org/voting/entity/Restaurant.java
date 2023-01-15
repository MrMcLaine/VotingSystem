package org.voting.entity;

import org.voting.entity.abstractEntity.AbstractNamedEntity;

import javax.persistence.*;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(name = "restaurant_unique_name_idx", columnNames = "name")})
public class Restaurant extends AbstractNamedEntity {

    public Restaurant() {
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName());
    }

    public Restaurant(String name) {
        this(null, name);
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
               ", name='" + name + '\'' +
               ", id=" + id +
               '}';
    }
}

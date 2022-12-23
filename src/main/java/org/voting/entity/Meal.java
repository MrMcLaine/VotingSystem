package org.voting.entity;

import org.voting.entity.abstractEntity.AbstractBaseEntity;
import org.voting.entity.abstractEntity.AbstractNamedEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Meal extends AbstractNamedEntity {
    private LocalDate date;
    private String description;
    private int price;
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(String description, int price, Restaurant restaurant) {
        this.date = LocalDate.now();
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Meal(Integer id, String name, LocalDate date, String description, int price, Restaurant restaurant) {
        super(id, name);
        this.date = date;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Meal{" +
               "description='" + description + '\'' +
               ", price=" + price +
               ", id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}

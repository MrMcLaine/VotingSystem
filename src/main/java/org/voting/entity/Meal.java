package org.voting.entity;

import org.voting.entity.AbstractEntity;

public class Meal extends AbstractEntity {
    private String description;
    private int price;

    public Meal(Integer id, String name, String description, int price) {
        super(id, name);
        this.price = price;
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

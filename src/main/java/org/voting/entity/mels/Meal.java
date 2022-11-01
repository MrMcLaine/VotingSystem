package org.voting.entity.mels;

import org.voting.entity.AbstractEntity;

public class Meal extends AbstractEntity {
    private int price;

    public Meal(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
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
                "id=" + id +
                ", name =" + name +
                ", price ='" + price +
                '}';
    }
}

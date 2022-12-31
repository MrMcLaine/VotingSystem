package org.voting.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.voting.View;
import org.voting.entity.abstractEntity.AbstractBaseEntity;
import org.voting.entity.abstractEntity.AbstractNamedEntity;
import org.voting.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "meals")
public class Meal extends AbstractNamedEntity {

    @Column(name = "date_meal", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDate date;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 2, max = 10)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull(groups = View.Persist.class)
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

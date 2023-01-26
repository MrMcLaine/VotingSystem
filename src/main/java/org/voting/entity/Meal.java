package org.voting.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.voting.entity.abstractEntity.AbstractBaseEntity;
import org.voting.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "meals")
public class Meal extends AbstractBaseEntity {

    @Column(name = "date_meal", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate dateMeal = LocalDate.now();

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 2, max = 1000)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(String description, int price, Restaurant restaurant) {
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Meal(Integer id, LocalDate date, String description, int price, Restaurant restaurant) {
        super(id);
        this.dateMeal = date;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Meal(Integer id, LocalDate date, String description, int price) {
        super(id);
        this.dateMeal = date;
        this.description = description;
        this.price = price;
    }

    public Meal(Integer id, String description, Integer price) {
        super(id);
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Meal{" +
               "dateMeal=" + dateMeal +
               ", description='" + description + '\'' +
               ", price=" + price +
               ", id=" + id +
               '}';
    }
}

package ru.restaurantVoting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * User: turhanow
 */
@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_date", "restaurant_id"}, name = "unique_menu")})
public class Menu extends AbstractBaseEntity {

    @NotNull
    @Column(name = "menu_date", nullable = false)
    private LocalDate date;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("name ASC")
    private List<Dish> dishes = Collections.emptyList();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public Menu() {
    }

    public Menu(Menu m) {
        this(m.getId(), m.getDate());
    }

    public Menu(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes.addAll(dishes);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Menu (" +
                "id=" + getId() +
                ", date=" + date +
                ')';
    }
}

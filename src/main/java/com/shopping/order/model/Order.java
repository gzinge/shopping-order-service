package com.shopping.order.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ORDER_TABLE")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="common_seq")
    //@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="order_seq")
    //@SequenceGenerator(name="order_seq", sequenceName="order_sequence", allocationSize=20)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToMany(targetEntity = Item.class, cascade=CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "ORDER_ITEM",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items = new HashSet<Item>();

    @Column(name = "ORDER_COST")
    private Double orderCost;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Double getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(Double orderCost) {
        this.orderCost = orderCost;
    }
}

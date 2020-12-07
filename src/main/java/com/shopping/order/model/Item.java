package com.shopping.order.model;

import javax.persistence.*;

@Entity
@Table(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="common_seq")
    @SequenceGenerator(name="common_seq", sequenceName="common_sequence", allocationSize=20)
    private Long id;

    private String name;

    private String description;

    private String image;

    private Double price;

    private String category;

    private Double qtyAvailable;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQtyAvailable() {
        return qtyAvailable;
    }

    public void setQtyAvailable(Double qtyAvailable) {
        this.qtyAvailable = qtyAvailable;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}

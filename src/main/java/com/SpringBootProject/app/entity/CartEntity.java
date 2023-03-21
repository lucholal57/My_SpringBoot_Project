package com.SpringBootProject.app.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "carts")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartDetailEntity> cartItems = new ArrayList<>();
    @Column(nullable = false)
    private Date date_created;
    @Column(nullable = true)
    private Date date_deleted;

    public CartEntity() {
    }

    public CartEntity(Long id, UserEntity user, String description,
                      BigDecimal price, List<CartDetailEntity> cartItems,
                      Date date_created, Date date_deleted) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.price = price;
        this.cartItems = cartItems;
        this.date_created = date_created;
        this.date_deleted = date_deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<CartDetailEntity> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartDetailEntity> cartItems) {
        this.cartItems = cartItems;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Date getDate_deleted() {
        return date_deleted;
    }

    public void setDate_deleted(Date date_deleted) {
        this.date_deleted = date_deleted;
    }
}

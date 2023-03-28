package com.SpringBootProject.app.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    /* La idea es sacar este codigo para crear una categoria normal y poder agregar productos a ciertas categorias.
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CategoryEntity parent;
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private Set<CategoryEntity> children;
    */
    @Column(nullable = false)
    private Date date_created;
    @Column(nullable = true)
    private Date date_deleted;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<ProductEntity> products;

    public CategoryEntity() {
    }

    public CategoryEntity(Long id, String name, Date date_created, Date date_deleted, List<ProductEntity> products) {
        this.id = id;
        this.name = name;
        this.date_created = date_created;
        this.date_deleted = date_deleted;
        this.products = products;
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

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
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

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date_created=" + date_created +
                ", date_deleted=" + date_deleted +
                '}';
    }
}

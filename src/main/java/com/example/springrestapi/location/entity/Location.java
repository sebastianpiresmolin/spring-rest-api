package com.example.springrestapi.location.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import java.util.Date;

@Entity
@Table(name = "location", schema = "mydatabase")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Size(max = 255)
    @NotNull
    @Column(name = "user_id", nullable = false)
    private String userId;

    @ColumnDefault("0")
    @Column(name = "is_private", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private boolean isPrivate;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "last_modified")
    private Date lastModified;

    @Size(max = 255)
    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "coordinate")
    private Point<G2D> coordinate;

    @ColumnDefault("0")
    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }


    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getCategoryId () { return categoryId; }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    public String getUserId() { return userId; }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public boolean isPrivate() { return isPrivate; }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }


    public Date getCreated() { return created; }

    public Date getLastModified() { return lastModified; }


    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Point<G2D> getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(Point<G2D> coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isDeleted() { return deleted; }

    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    @PrePersist
    protected void onCreate() {
        this.created = new Date();
        this.lastModified = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModified = new Date();
    }


}

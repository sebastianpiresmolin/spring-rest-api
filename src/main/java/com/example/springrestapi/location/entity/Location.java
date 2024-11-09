package com.example.springrestapi.location.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

import org.springframework.data.geo.Point;

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
    private boolean isPrivate;

    @NotNull
    @Column(name = "created", nullable = false)
    private Date created;

    @NotNull
    @Column(name = "last_modified", nullable = false)
    private Date lastModified;

    @Size(max = 255)
    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "coordinates")
    private Point coordinates;

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

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() { return lastModified; }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Point getCoordinates() { return coordinates; }

    public void setCoordinates(double longitude, double latitude) {
        this.coordinates = new Point(longitude, latitude);
    }


}

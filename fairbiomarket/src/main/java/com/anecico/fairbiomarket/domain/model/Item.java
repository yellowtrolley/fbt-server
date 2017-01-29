package com.anecico.fairbiomarket.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.katharsis.resource.annotations.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Created by pablo on 1/28/17.
 */
@JsonApiResource(type = "items")
public class Item {
    @Id
    @JsonApiId
    private String id;

    @JsonProperty
    private String name;

    @DBRef
    @JsonApiToMany
    private List<Category> categories;

    @JsonProperty
    private String description;

    @JsonProperty
    private String longDescription;

    @JsonProperty
    private String brand;

    @JsonProperty
    private Double price;

    @JsonProperty
    private Double rating;

    @JsonApiToOne
    @JsonApiIncludeByDefault
    @DBRef
    private User seller;

    @JsonProperty
    private List<String> imageids;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public List<String> getImageids() {
        return imageids;
    }

    public void setImageids(List<String> imageids) {
        this.imageids = imageids;
    }
}

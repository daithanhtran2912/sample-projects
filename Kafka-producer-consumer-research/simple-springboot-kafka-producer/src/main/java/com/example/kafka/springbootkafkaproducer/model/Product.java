package com.example.kafka.springbootkafkaproducer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    @JsonProperty
    private String ean;
    @JsonProperty
    private String upc;
    @JsonProperty
    private String image;
    @JsonProperty
    private List<Image> images;
    @JsonProperty
    private Boolean netPrice;
    @JsonProperty
    private String taxes;
    @JsonProperty
    private String price;
    @JsonProperty
    private List<String> categories;
    @JsonProperty
    private List<String> tags;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Boolean getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Boolean netPrice) {
        this.netPrice = netPrice;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ean='" + ean + '\'' +
                ", upc='" + upc + '\'' +
                ", image='" + image + '\'' +
                ", images=" + images +
                ", netPrice=" + netPrice +
                ", taxes='" + taxes + '\'' +
                ", price='" + price + '\'' +
                ", categories=" + categories +
                ", tags=" + tags +
                '}';
    }
}

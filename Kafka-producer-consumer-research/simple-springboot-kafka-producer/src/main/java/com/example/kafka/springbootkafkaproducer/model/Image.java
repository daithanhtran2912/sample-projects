package com.example.kafka.springbootkafkaproducer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Image implements Serializable {

    @JsonProperty
    private String title;
    @JsonProperty
    private String description;
    @JsonProperty
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Image{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

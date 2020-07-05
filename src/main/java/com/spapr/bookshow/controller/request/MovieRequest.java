package com.spapr.bookshow.controller.request;

import javax.validation.constraints.NotBlank;

public class MovieRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String company;
    @NotBlank
    private String director;
    @NotBlank
    private String producer;

    private String initialReleaseDate;

    private String actors;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getInitialReleaseDate() {
        return initialReleaseDate;
    }

    public void setInitialReleaseDate(String initialReleaseDate) {
        this.initialReleaseDate = initialReleaseDate;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }
}

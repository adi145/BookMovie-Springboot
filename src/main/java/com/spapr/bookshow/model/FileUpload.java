package com.spapr.bookshow.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class FileUpload {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;

    private String fileType;

    private String movieName;

    @Lob
    private String description;

    private String company;

    private String director;

    private String producer;

    private String initialReleaseDate;

    private String actors;

    @JsonIgnore
    @Lob
    private byte[] data;

    public FileUpload() {

    }

    public FileUpload(String fileName, String fileType, byte[] data, String movieName, String description, String company, String director,String producer, String initialReleaseDate, String actors) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.movieName = movieName;
        this.company = company;
        this.description = description;
        this.director = director;
        this.producer = producer;
        this.initialReleaseDate = initialReleaseDate;
        this.actors = actors;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
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


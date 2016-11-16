package com.developedbyryan.camp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StatePark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Size(min = 5, max = 30)
    private String name;

    // Will want to remove validation messages from this class
    // TODO: see https://teamtreehouse.com/library/displaying-validation-messages
    // under teacher's notes for instructions

//    @OneToOne
//    private Photo mainPhoto;

    @OneToMany(mappedBy = "statePark")
    private List<Photo> photos = new ArrayList<>();

    @Column
    private byte[] bytes;

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

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

//    public Photo getMainPhoto() {
//        return mainPhoto;
//    }
//
//    public void setMainPhoto(Photo mainPhoto) {
//        this.mainPhoto = mainPhoto;
//    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}

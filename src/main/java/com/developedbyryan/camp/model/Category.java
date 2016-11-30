package com.developedbyryan.camp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Size(min = 5, max = 30)
    private String name;

    @NotNull
    @Pattern(regexp = "#[0-9a-fA-F]{6}")
    private String colorCode;

    @OneToMany(mappedBy = "category")
    private List<StatePark> stateParks = new ArrayList<>();

    public Category() {}

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

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public List<StatePark> getStateParks() {
        return stateParks;
    }

    public void setStateParks(List<StatePark> stateParks) {
        this.stateParks = stateParks;
    }
}

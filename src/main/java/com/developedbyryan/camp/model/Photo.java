package com.developedbyryan.camp.model;

import javax.persistence.*;

@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private byte[] bytes;

    @ManyToOne
    private StatePark statePark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public StatePark getStatePark() {
        return statePark;
    }

    public void setStatePark(StatePark statePark) {
        this.statePark = statePark;
    }
}

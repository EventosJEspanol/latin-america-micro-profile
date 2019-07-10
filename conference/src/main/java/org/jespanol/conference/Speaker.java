package org.jespanol.conference;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;

@Entity
public class Speaker {

    @Column
    private Integer id;

    @Column
    private String name;

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

    @Override
    public String toString() {
        return "Speaker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

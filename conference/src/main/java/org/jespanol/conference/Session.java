package org.jespanol.conference;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;

@Entity
public class Session {

    @Column
    private String id;

    @Column
    private String name;

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

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

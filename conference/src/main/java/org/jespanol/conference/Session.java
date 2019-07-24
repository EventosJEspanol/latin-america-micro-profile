package org.jespanol.conference;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;

import java.util.Objects;

@Entity
public class Session {

    @Column
    private String id;

    @Column
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public static Session of(SessionDTO dto) {
        Objects.requireNonNull(dto, "dto is required");
        Session session = new Session();
        session.id = dto.getId();
        session.name = dto.getName();
        return session;
    }
}

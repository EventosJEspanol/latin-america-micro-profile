package org.jespanol.conference;

import java.util.Objects;

public class SessionDTO {

    private String id;

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
        return "SessionDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static SessionDTO of(Session session) {
        Objects.requireNonNull(session, "session is required");
        SessionDTO dto = new SessionDTO();
        dto.setId(session.getId());
        dto.setName(session.getName());
        return dto;
    }
}

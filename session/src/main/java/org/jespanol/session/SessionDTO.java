package org.jespanol.session;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class SessionDTO {

    private String id;

    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name is required")
    private String name;

    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Conference is required")
    private String conference;

    @NotNull(message = "Speaker is required")
    private Integer speaker;

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

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public Integer getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Integer speaker) {
        this.speaker = speaker;
    }

    @Override
    public String toString() {
        return "SessionDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", conference='" + conference + '\'' +
                ", speaker=" + speaker +
                '}';
    }

    public static SessionDTO of(Session session) {
        Objects.requireNonNull(session, "session is required");
        SessionDTO dto = new SessionDTO();
        dto.id = session.getId();
        dto.name = session.getName();
        dto.title = session.getTitle();
        dto.description = session.getDescription();
        dto.conference = session.getConference();
        dto.speaker = session.getSpeaker();
        return dto;
    }
}

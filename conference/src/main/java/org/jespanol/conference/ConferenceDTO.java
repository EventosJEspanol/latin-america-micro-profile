package org.jespanol.conference;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Year;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConferenceDTO {

    private String id;

    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name is required")
    private String name;

    @NotBlank(message = "City cannot be blank")
    @NotNull(message = "City is required")
    private String city;

    @NotBlank(message = "Link cannot be blank")
    @NotNull(message = "Link is required")
    private String link;

    @NotBlank(message = "Year cannot be blank")
    @NotNull(message = "Year is required")
    private Integer year;

    private List<SpeakerDTO> speakers;

    private List<SessionDTO> sessions;


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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<SpeakerDTO> getSpeakers() {
        if (speakers == null) {
            return Collections.emptyList();
        }
        return speakers;
    }

    public void setSpeakers(List<SpeakerDTO> speakers) {
        this.speakers = speakers;
    }

    public List<SessionDTO> getSessions() {
        if (sessions == null) {
            return Collections.emptyList();
        }
        return sessions;
    }

    public void setSessions(List<SessionDTO> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "ConferenceDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", link='" + link + '\'' +
                ", year=" + year +
                ", speakers=" + speakers +
                ", sessions=" + sessions +
                '}';
    }

    public static ConferenceDTO of(Conference conference) {
        Objects.requireNonNull(conference, "conference is required");
        ConferenceDTO dto = new ConferenceDTO();
        dto.setId(conference.getId());
        dto.setName(conference.getName());
        dto.setCity(conference.getCity());
        dto.setLink(conference.getLink());
        dto.setYear(conference.getYear().map(Year::getValue).orElse(null));
        dto.setSessions(conference.getSessions().stream().map(SessionDTO::of).collect(Collectors.toList()));
        dto.setSpeakers(conference.getSpeakers().stream().map(SpeakerDTO::of).collect(Collectors.toList()));
        return dto;
    }

}

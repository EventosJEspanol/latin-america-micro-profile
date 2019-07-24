package org.jespanol.conference;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Convert;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

import java.time.Year;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
public class Conference {

    @Id
    @Convert(ObjectIdConverter.class)
    private String id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String link;

    @Column
    @Convert(YearConverter.class)
    private Year year;

    @Column
    private List<Speaker> speakers;

    @Column
    private List<Session> sessions;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getLink() {
        return link;
    }

    public Optional<Year> getYear() {
        return Optional.ofNullable(year);
    }

    public List<Speaker> getSpeakers() {
        if (speakers == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(speakers);
    }

    public List<Session> getSessions() {
        if (sessions == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(sessions);
    }

    public void update(Conference conference) {
        this.city = conference.city;
        this.name = conference.name;
        this.year = conference.year;
        this.speakers = conference.speakers;
        this.sessions = conference.sessions;
        this.link = conference.link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Conference conference = (Conference) o;
        return Objects.equals(id, conference.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Conference{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", link='" + link + '\'' +
                ", year=" + year +
                ", speakers=" + speakers +
                ", sessions=" + sessions +
                '}';
    }

    public static Conference of(ConferenceDTO dto) {
        Objects.requireNonNull(dto, "dto is required");
        Conference conference = new Conference();
        conference.id = dto.getId();
        conference.name = dto.getName();
        conference.city = dto.getCity();
        conference.link = dto.getLink();
        conference.year = Year.of(dto.getYear());
        conference.sessions = dto.getSessions().stream().map(Session::of).collect(Collectors.toList());
        conference.speakers = dto.getSpeakers().stream().map(Speaker::of).collect(Collectors.toList());
        return conference;
    }
}

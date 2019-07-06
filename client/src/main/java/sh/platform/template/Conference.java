package sh.platform.template;

import java.time.Year;
import java.util.List;
import java.util.Objects;

public class Conference {

    private String id;

    private String name;

    private String city;

    private String link;

    private Year year;

    private List<Speaker> speakers;

    private List<Session> sessions;

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

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
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
}

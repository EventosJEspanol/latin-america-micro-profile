package org.jespanol.client.conference;

import org.thymeleaf.util.StringUtils;

import javax.ws.rs.FormParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Conference {

    @FormParam("id")
    private String id;

    @FormParam("name")
    private String name;

    @FormParam("city")
    private String city;

    @FormParam("link")
    private String link;

    @FormParam("year")
    private Integer year;

    @FormParam("speakers")
    private List<Integer> speakers = new ArrayList<>();

    @FormParam("presentations")
    private List<String> sessions = new ArrayList<>();

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

    public List<Integer> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Integer> speakers) {
        this.speakers = speakers;
    }

    public List<String> getSessions() {
        return sessions;
    }

    public void setSessions(List<String> sessions) {
        this.sessions = sessions;
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
                '}';
    }

    public boolean isIdEmpty() {
        if (StringUtils.isEmpty(id)) {
            this.id = null;
            return true;
        }
        return false;
    }
}

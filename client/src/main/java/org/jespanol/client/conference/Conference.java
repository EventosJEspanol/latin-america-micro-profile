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
    private List<Integer> speakersIds = new ArrayList<>();

    @FormParam("presentations")
    private List<String> sessionsIds = new ArrayList<>();

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

    public List<Integer> getSpeakersIds() {
        return speakersIds;
    }

    public void setSpeakersIds(List<Integer> speakersIds) {
        this.speakersIds = speakersIds;
    }

    public List<String> getSessionsIds() {
        return sessionsIds;
    }

    public void setSessionsIds(List<String> sessionsIds) {
        this.sessionsIds = sessionsIds;
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
                ", speakersIds=" + speakersIds +
                ", sessionsIds=" + sessionsIds +
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

package org.jespanol.conference.speaker;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Convert;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Speaker {

    @Id
    @Convert(ObjectIdConverter.class)
    private String id;

    @Column
    private String name;

    @Column
    private String bio;

    @Column
    private List<String> links;

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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getLinks() {
        if (links == null) {
            return Collections.emptyList();
        }
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }


    public void update(Speaker speaker) {
        this.bio = speaker.bio;
        this.name = speaker.name;
        this.links = speaker.links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Speaker speaker = (Speaker) o;
        return Objects.equals(id, speaker.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", links=" + links +
                '}';
    }

}

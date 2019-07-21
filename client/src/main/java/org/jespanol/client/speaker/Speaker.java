package org.jespanol.client.speaker;

import javax.ws.rs.FormParam;
import java.util.Objects;

public class Speaker {

    @FormParam("id")
    private Integer id;

    @FormParam("name")
    private String name;

    @FormParam("bio")
    private String bio;

    @FormParam("twitter")
    private String twitter;

    @FormParam("github")
    private String github;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Speaker that = (Speaker) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", twitter='" + twitter + '\'' +
                ", github='" + github + '\'' +
                '}';
    }
}




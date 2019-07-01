package org.jespanol.speaker;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "speaker")
public class Speaker {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    @NotBlank
    private String name;

    @Column
    private String bio;

    @Column
    private String twitter;

    @Column
    private String github;


    public void update(Speaker speaker) {
        this.twitter = speaker.twitter;
        this.name = speaker.name;
        this.github = speaker.github;
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




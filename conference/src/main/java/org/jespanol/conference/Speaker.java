package org.jespanol.conference;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;

import java.util.Objects;

@Entity
public class Speaker {

    @Column
    private Integer id;

    @Column
    private String name;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Speaker of(SpeakerDTO dto) {
        Objects.requireNonNull(dto, "dto is required");
        Speaker speaker = new Speaker();
        speaker.id = dto.getId();
        speaker.name = dto.getName();
        return speaker;
    }

}

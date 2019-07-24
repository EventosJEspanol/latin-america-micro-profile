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

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
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

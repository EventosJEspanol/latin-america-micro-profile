package org.jespanol.conference;

import java.util.Objects;

public class SpeakerDTO {

    private Integer id;

    private String name;

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

    @Override
    public String toString() {
        return "SpeakerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static SpeakerDTO of(Speaker speaker) {
        Objects.requireNonNull(speaker, "speaker is required");
        SpeakerDTO dto = new SpeakerDTO();
        dto.setId(speaker.getId());
        dto.setName(speaker.getName());
        return dto;
    }
}

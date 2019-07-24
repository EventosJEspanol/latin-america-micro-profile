package org.jespanol.speaker;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class SpeakerDTO {

    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name is required")
    private String name;

    @NotBlank(message = "Bio cannot be blank")
    @NotNull(message = "Bio is required")
    private String bio;

    private String twitter;

    private String github;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getGithub() {
        return github;
    }

    @Override
    public String toString() {
        return "SpeakerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", twitter='" + twitter + '\'' +
                ", github='" + github + '\'' +
                '}';
    }

    public static SpeakerDTO of(Speaker speaker) {
        Objects.requireNonNull(speaker, "speaker is required");
        SpeakerDTO dto = new SpeakerDTO();
        dto.id = speaker.getId();
        dto.name = speaker.getName();
        dto.bio = speaker.getBio();
        dto.twitter = speaker.getTwitter();
        dto.github = speaker.getGithub();
        return dto;
    }

}




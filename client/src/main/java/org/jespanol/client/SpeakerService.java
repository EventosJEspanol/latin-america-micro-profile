package org.jespanol.client;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class SpeakerService {

    public List<Speaker> findAll(){
        List<Speaker> speakers = new ArrayList<>();

        Speaker speaker = new Speaker();
        speaker.setId(1);
        speaker.setBio("bio");
        speaker.setGithub("git");
        speaker.setName("name");
        speaker.setTwitter("otavio");
        speakers.add(speaker);
        return speakers;
    }

    public Speaker findById(Integer id){
        Speaker speaker = new Speaker();
        speaker.setId(1);
        speaker.setBio("bio");
        speaker.setGithub("git");
        speaker.setName("name");
        speaker.setTwitter("otavio");
        return speaker;
    }

    public Speaker update( Integer id, Speaker speaker) {
        return speaker;
    }

    public Response remove(Integer id) {
        return Response.status(12).build();
    }

    public Speaker insert(Speaker speaker){
        return speaker;
    }

}

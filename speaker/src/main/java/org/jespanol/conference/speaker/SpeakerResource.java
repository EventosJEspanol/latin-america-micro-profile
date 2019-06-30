package org.jespanol.conference.speaker;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.status;

@Path("speakers")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SpeakerResource {

    @Inject
    private SpeakerRepository speakerRepository;

    @GET
    public List<Speaker> doGet() {
        return speakerRepository.findAll();
    }

    @GET
    @Path("{id}")
    public Speaker findById(@PathParam("id") String id) {
        final Optional<Speaker> conference = speakerRepository.findById(id);
        return conference.orElseThrow(this::notFound);
    }

    @PUT
    @Path("{id}")
    public Speaker update(@PathParam("id") String id, Speaker speakerUpdated) {
        final Optional<Speaker> optional = speakerRepository.findById(id);
        final Speaker speaker = optional.orElseThrow(() -> notFound());
        speaker.update(speakerUpdated);
        speakerRepository.save(speaker);
        return speaker;
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") String id) {
        speakerRepository.deleteById(id);
        return status(NO_CONTENT).build();
    }

    @POST
    public Speaker insert(Speaker conference) {
        return speakerRepository.save(conference);
    }

    private WebApplicationException notFound() {
        return new WebApplicationException(Response.Status.NOT_FOUND);
    }
}

package org.jespanol.speaker;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
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
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.status;

@Path("speakers")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON+ "; charset=UTF-8")
public class SpeakerResource {

    @Inject
    private SpeakerService speakerService;

    @GET
    public List<SpeakerDTO> findAll() {
        return speakerService.findAll()
                .stream().map(SpeakerDTO::of)
                .collect(Collectors.toList());
    }

    @GET
    @Path("{id}")
    public SpeakerDTO findById(@PathParam("id") Integer id) {
        final Optional<Speaker> conference = speakerService.find(id);
        return conference.map(SpeakerDTO::of).orElseThrow(this::notFound);
    }

    @PUT
    @Path("{id}")
    public SpeakerDTO update(@PathParam("id") Integer id, @Valid Speaker speakerUpdated) {
        final Optional<Speaker> optional = speakerService.find(id);
        final Speaker speaker = optional.orElseThrow(() -> notFound());
        speaker.update(speakerUpdated);
        speakerService.update(speaker);
        return SpeakerDTO.of(speaker);
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        speakerService.delete(id);
        return status(NO_CONTENT).build();
    }

    @POST
    public SpeakerDTO insert(@Valid SpeakerDTO speaker) {
        return SpeakerDTO.of(speakerService.insert(Speaker.of(speaker)));
    }

    private WebApplicationException notFound() {
        return new WebApplicationException(Response.Status.NOT_FOUND);
    }

}

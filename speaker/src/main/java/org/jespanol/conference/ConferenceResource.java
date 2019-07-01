package org.jespanol.conference;

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

@Path("conferences")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConferenceResource {

    @Inject
    private ConferenceService conferenceService;

    @GET
    public List<Speaker> doGet() {
        return conferenceService.findAll();
    }

    @GET
    @Path("{id}")
    public Speaker findById(@PathParam("id") Integer id) {
        final Optional<Speaker> conference = conferenceService.find(id);
        return conference.orElseThrow(this::notFound);
    }

    @PUT
    @Path("{id}")
    public Speaker update(@PathParam("id") Integer id, Speaker speakerUpdated) {
        final Optional<Speaker> optional = conferenceService.find(id);
        final Speaker speaker = optional.orElseThrow(() -> notFound());
        speaker.update(speakerUpdated);
        conferenceService.update(speaker);
        return speaker;
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        conferenceService.delete(id);
        return status(NO_CONTENT).build();
    }

    @POST
    public Speaker insert(Speaker speaker) {
        return conferenceService.insert(speaker);
    }

    private WebApplicationException notFound() {
        return new WebApplicationException(Response.Status.NOT_FOUND);
    }

}

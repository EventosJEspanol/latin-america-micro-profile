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
    private ConferenceRepository conferenceRepository;

    @GET
    public List<Conference> doGet() {
        return conferenceRepository.findAll();
    }

    @GET
    @Path("{id}")
    public Conference findById(@PathParam("id") String id) {
        final Optional<Conference> conference = conferenceRepository.findById(id);
        return conference.orElseThrow(this::notFound);
    }

    @PUT
    @Path("{id}")
    public Conference update(@PathParam("id") String id, Conference conferenceUpdated) {
        final Optional<Conference> optional = conferenceRepository.findById(id);
        final Conference conference = optional.orElseThrow(() -> notFound());
        conference.update(conferenceUpdated);
        conferenceRepository.save(conference);
        return conference;
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") String id) {
        conferenceRepository.deleteById(id);
        return status(NO_CONTENT).build();
    }

    @POST
    public Conference insert(Conference conference) {
        return conferenceRepository.save(conference);
    }

    private WebApplicationException notFound() {
        return new WebApplicationException(Response.Status.NOT_FOUND);
    }
}

package org.jespanol.session;

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
import java.util.UUID;

import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.status;

@Path("sessions")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON+ "; charset=UTF-8")
public class SessionResource {

    @Inject
    private SessionRepository speakerRepository;

    @GET
    public List<Session> findAll() {
        return speakerRepository.findAll();
    }

    @GET
    @Path("{id}")
    public Session findById(@PathParam("id") String id) {
        final Optional<Session> conference = speakerRepository.findById(id);
        return conference.orElseThrow(this::notFound);
    }

    @PUT
    @Path("{id}")
    public Session update(@PathParam("id") String id, Session sessionUpdated) {
        final Optional<Session> optional = speakerRepository.findById(id);
        final Session speaker = optional.orElseThrow(() -> notFound());
        speaker.update(sessionUpdated);
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
    public Session insert(Session session) {
        session.setId(UUID.randomUUID().toString());
        return speakerRepository.save(session);
    }

    private WebApplicationException notFound() {
        return new WebApplicationException(Response.Status.NOT_FOUND);
    }
}

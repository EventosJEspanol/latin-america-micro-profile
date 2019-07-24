package org.jespanol.conference;

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

@Path("conferences")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON+ "; charset=UTF-8")
public class ConferenceResource {

    @Inject
    private ConferenceRepository conferenceRepository;

    @GET
    public List<ConferenceDTO> findAll() {
        return conferenceRepository.findAll().stream()
                .map(ConferenceDTO::of)
                .collect(Collectors.toList());
    }

    @GET
    @Path("{id}")
    public ConferenceDTO findById(@PathParam("id") String id) {
        final Optional<Conference> conference = conferenceRepository.findById(id);
        return conference.map(ConferenceDTO::of).orElseThrow(this::notFound);
    }

    @PUT
    @Path("{id}")
    public ConferenceDTO update(@PathParam("id") String id, @Valid ConferenceDTO conferenceUpdated) {
        final Optional<Conference> optional = conferenceRepository.findById(id);
        final Conference conference = optional.orElseThrow(() -> notFound());
        conference.update(Conference.of(conferenceUpdated));
        conferenceRepository.save(conference);
        return ConferenceDTO.of(conference);
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") String id) {
        conferenceRepository.deleteById(id);
        return status(NO_CONTENT).build();
    }

    @POST
    public ConferenceDTO insert(@Valid ConferenceDTO conference) {
        return ConferenceDTO.of(conferenceRepository.save(Conference.of(conference)));
    }

    private WebApplicationException notFound() {
        return new WebApplicationException(Response.Status.NOT_FOUND);
    }
}

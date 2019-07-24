package org.jespanol.session;

import org.elasticsearch.index.query.QueryBuilder;
import org.jnosql.artemis.elasticsearch.document.ElasticsearchTemplate;
import org.jnosql.artemis.util.StringUtils;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.status;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Path("sessions")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
public class SessionResource {

    private static Logger LOGGER = Logger.getLogger(SessionResource.class.getName());

    @Inject
    private SessionRepository speakerRepository;

    @Inject
    private ElasticsearchTemplate template;

    @GET
    public List<SessionDTO> findAll(@QueryParam("search") String search) {
        LOGGER.info("searching with the field: " + search);
        if (StringUtils.isNotBlank(search)) {
            QueryBuilder queryBuilder = boolQuery()
                    .should(termQuery("name", search))
                    .should(termQuery("title", search))
                    .should(termQuery("description", search));

            LOGGER.info("the query: " + queryBuilder);
            List<Session> sessions = template.search(queryBuilder, "Session");
            LOGGER.info("the result: " + sessions);
            return sessions.stream()
                    .map(SessionDTO::of)
                    .collect(Collectors.toList());
        }
        return speakerRepository.findAll().stream()
                .map(SessionDTO::of).collect(Collectors.toList());
    }

    @GET
    @Path("{id}")
    public Session findById(@PathParam("id") String id) {
        final Optional<Session> conference = speakerRepository.findById(id);
        return conference.orElseThrow(this::notFound);
    }

    @PUT
    @Path("{id}")
    public SessionDTO update(@PathParam("id") String id, @Valid SessionDTO sessionUpdated) {
        final Optional<Session> optional = speakerRepository.findById(id);
        final Session session = optional.orElseThrow(() -> notFound());
        session.update(Session.of(sessionUpdated));
        speakerRepository.save(session);
        return SessionDTO.of(session);
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") String id) {
        speakerRepository.deleteById(id);
        return status(NO_CONTENT).build();
    }

    @POST
    public SessionDTO insert(@Valid SessionDTO session) {
        session.setId(UUID.randomUUID().toString());
        return SessionDTO.of(speakerRepository.save(Session.of(session)));
    }

    private WebApplicationException notFound() {
        return new WebApplicationException(Response.Status.NOT_FOUND);
    }
}

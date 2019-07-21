package org.jespanol.client.session;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jespanol.client.conference.ConferenceService;
import org.jespanol.client.speaker.SpeakerService;
import org.thymeleaf.util.StringUtils;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Optional;

@Controller
@Path("session")
public class SessionController {
    @Inject
    private Models models;

    @Inject
    @RestClient
    private SessionService sessionService;

    @Inject
    @RestClient
    private ConferenceService conferenceService;

    @Inject
    @RestClient
    private SpeakerService speakerService;

    @GET
    @View("session.html")
    public void home() {
        this.models.put("presentations", sessionService.findAll());
    }


    @Path("add")
    @GET
    @View("session-add.html")
    public void add() {
        this.models.put("presentation", new Session());
        this.models.put("conferences", conferenceService.findAll());
        this.models.put("speakers", speakerService.findAll());
    }

    @Path("delete/{id}")
    @GET
    @View("session.html")
    public void delete(@PathParam("id") String id) {
        sessionService.remove(id);
        this.models.put("presentations", sessionService.findAll());
    }

    @Path("edit/{id}")
    @GET
    @View("session-add.html")
    public void edit(@PathParam("id") String id) {
        final Session session = Optional.ofNullable(sessionService.findById(id))
                .orElse(new Session());
        this.models.put("presentation", session);
        this.models.put("conferences", conferenceService.findAll());
        this.models.put("speakers", speakerService.findAll());
    }

    @Path("add")
    @POST
    @View("session.html")
    public void add(@BeanParam Session session) {
        if (StringUtils.isEmpty(session.getId())) {
            sessionService.insert(session);
        } else {
            sessionService.update(session.getId(), session);
        }
        this.models.put("presentations", sessionService.findAll());
    }

}

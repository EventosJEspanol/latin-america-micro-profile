package org.jespanol.client.conference;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jespanol.client.session.Session;
import org.jespanol.client.session.SessionService;
import org.jespanol.client.speaker.SpeakerService;
import org.thymeleaf.util.StringUtils;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Controller
@Path("conference")
@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
public class ConferenceController {
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
    @View("conference.html")
    public void home() {
        this.models.put("conferences", conferenceService.findAll());
    }


    @Path("add")
    @GET
    @View("conference-add.html")
    public void add() {
        this.models.put("conference", new Conference());
        this.models.put("speakers", speakerService.findAll());
        this.models.put("presentations", sessionService.findAll());
    }

    @Path("delete/{id}")
    @GET
    @View("conference.html")
    public void delete(@PathParam("id") String id) {
        conferenceService.remove(id);
        this.models.put("conferences", conferenceService.findAll());
    }

    @Path("edit/{id}")
    @GET
    @View("conference-add.html")
    public void edit(@PathParam("id") String id) {
        final Conference conference = Optional.ofNullable(conferenceService.findById(id))
                .orElse(new Conference());
        this.models.put("conference", conference);
        this.models.put("speakers", speakerService.findAll());
        this.models.put("presentations", sessionService.findAll());
    }

    @Path("add")
    @POST
    @View("conference.html")
    public void add(@BeanParam Conference conference) {
        conference.update(speakerService, sessionService);
        if (conference.isIdEmpty()) {
            conferenceService.insert(conference);
        } else {
            conferenceService.update(conference.getId(), conference);
        }
        this.models.put("conferences", conferenceService.findAll());
    }
}

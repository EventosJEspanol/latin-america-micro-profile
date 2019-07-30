package org.jespanol.client.health;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Health;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Health
@ApplicationScoped
public class ConferenceHealthCheck extends AbstractHealthCheck {

    @Inject
    @ConfigProperty(name = "org.jespanol.client.conference.ConferenceService/mp-rest/url")
    private String url;

    private Client client;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newClient();
    }


    @Override
    Client getClient() {
        return client;
    }

    @Override
    String getUrl() {
        return url;
    }

    @Override
    String getServiceName() {
        return "Conference Service";
    }
}

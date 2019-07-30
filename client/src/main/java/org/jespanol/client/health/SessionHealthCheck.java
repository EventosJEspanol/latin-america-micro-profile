package org.jespanol.client.health;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Health
@ApplicationScoped
public class SessionHealthCheck implements HealthCheck {

    @Inject
    @ConfigProperty(name = "org.jespanol.client.session.SessionService/mp-rest/url")
    private String url;

    private Client client;

    @PostConstruct
    public void init() {
        this.client = ClientBuilder.newClient();
    }

    @Override
    public HealthCheckResponse call() {
        try {
            long start = System.currentTimeMillis();
            Response response = client.target(url).request(MediaType.TEXT_PLAIN_TYPE)
                    .get();
            long end = System.currentTimeMillis() - start;
            return HealthCheckResponse.named("Session Service")
                    .withData("service", "available")
                    .withData("time millis", end)
                    .withData("status", response.getStatus())
                    .withData("status", response.getStatusInfo().toEnum().toString())
                    .up()
                    .build();
        } catch (Exception exp) {
            return HealthCheckResponse.named("Session Service")
                    .withData("services", "not available")
                    .down()
                    .build();
        }
    }
}

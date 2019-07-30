package org.jespanol.client.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

abstract class AbstractHealthCheck implements HealthCheck {


    abstract Client getClient();

    abstract String getUrl();

    abstract String getServiceName();

    @Override
    public HealthCheckResponse call() {
        try {
            long start = System.currentTimeMillis();
            Response response = getClient().target(getUrl()).request(MediaType.TEXT_PLAIN_TYPE)
                    .get();
            long end = System.currentTimeMillis() - start;
            return HealthCheckResponse.named(getServiceName())
                    .withData("service", "available")
                    .withData("time millis", end)
                    .withData("status", response.getStatus())
                    .withData("status", response.getStatusInfo().toEnum().toString())
                    .up()
                    .build();
        } catch (Exception exp) {
            return HealthCheckResponse.named(getServiceName())
                    .withData("services", "not available")
                    .down()
                    .build();
        }
    }
}

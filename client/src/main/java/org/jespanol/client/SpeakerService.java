package org.jespanol.client;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("speakers")
public interface SpeakerService {

    @GET
    List<Speaker> findAll();

    @GET
    @Path("{id}")
    Speaker findById(@PathParam("id") Integer id);

    @PUT
    @Path("{id}")
    Speaker update(@PathParam("id") Integer id, Speaker speaker) ;

    @DELETE
    @Path("{id}")
    Response remove(@PathParam("id") Integer id);

    @POST
    Speaker insert(Speaker speaker);

}

package sh.platform.template;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("sessions")
public interface SessionService {

    @GET
    List<Session> findAll();

    @GET
    @Path("{id}")
    Session findById(@PathParam("id") String id);

    @PUT
    @Path("{id}")
    Session update(@PathParam("id") String id, Session session);

    @DELETE
    @Path("{id}")
    Response remove(@PathParam("id") String id);

    @POST
    Session insert(Session session);

}

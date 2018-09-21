package kz.edu.nu.cs.exericse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

@Path("/items")
public class MyService {

    private Map<String, String> entries = new ConcurrentHashMap<>();

    @GET
    public Response getItems() {
        Gson gson = new Gson();
        String json = gson.toJson(entries);
        return Response.ok(json).build();
    }

    @POST
    public Response createEntry(@FormParam("key") String key, @FormParam("value") String value) {
        entries.put(key, value);
        return Response.status(Status.CREATED).build();
    }

    @GET
    @Path("{id: [a-zA-Z]+}")
    public Response getById(@PathParam("id") String id) {
        String r = "Requested for item with id \"" + id + "\"";
        return Response.ok(r).build();
    }
}

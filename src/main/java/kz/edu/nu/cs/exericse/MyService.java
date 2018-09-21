package kz.edu.nu.cs.exericse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

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
        if (!isValidKey(key) || !isValidValue(value) || entries.containsKey(key))
            return Response.status(Status.FORBIDDEN).build();
        entries.put(key, value);
        return Response.status(Status.CREATED).build();
    }

    @GET
    @Path("{key: \\w+}")
    public Response getById(@PathParam("key") String key) {
        String r = "Requested for key \"" + key + "\"";
        if (!entries.containsKey(key))
            return Response.status(Status.NOT_FOUND).build();
        return Response.ok(r).build();
    }

    private boolean isValidKey(String string) {
        return string.matches("^\\w+$");
    }

    private boolean isValidValue(String string) {
        return string.matches("^[\\w ]+$");
    }
}

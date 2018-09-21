package kz.edu.nu.cs.exericse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

@Path("/items")
public class MyService {
    
    private Map<String, String> entries = new ConcurrentHashMap<String, String>();
    
    @GET
    public Response getItems() {
        Gson gson = new Gson();
        String json = gson.toJson(entries);
        return Response.ok(json).build();
    }
    
    @POST
    public Response createEntry(@FormParam("mykey") String mykey, @FormParam("myentry") String myentry) {
        entries.put(mykey, myentry);
        return Response.status(Status.CREATED).build();
    }
}

package kz.edu.nu.cs.exericse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/items")
public class MyService {
    
    private Map<String, String> entries = new ConcurrentHashMap<String, String>();
    
    @GET
    public Response getItems() {
        String r = "JAX-RS: Hello, world";
        return Response.ok(r).build();
    }
}

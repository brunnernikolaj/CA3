/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.User;
import facades.UserFacade;
import java.util.Arrays;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Nikolaj
 */
@Path("register")
public class RegisterService {

    @Context
    private UriInfo context;
    
    UserFacade facade = new UserFacade();

    /**
     * Creates a new instance of RegisterService
     */
    public RegisterService() {
    }

    /**
     * Retrieves representation of an instance of security.RegisterService
     *
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("application/json")
    public Response register(String request) {
        JsonObject json = new JsonParser().parse(request).getAsJsonObject();
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        
        facade.registerUser(new User(username, password, Arrays.asList("User")));
        
        return Response.ok().build();
    }

}

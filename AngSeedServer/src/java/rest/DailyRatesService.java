/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import facades.CurrencyFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author Nikolaj
 */
@Path("currency")
public class DailyRatesService {

    @Context
    private UriInfo context;

    private CurrencyFacade facade = new CurrencyFacade();
    private Gson gson = new Gson();
    /**
     * Creates a new instance of DailyRatesService
     */
    public DailyRatesService() {
    }

    /**
     * Retrieves representation of an instance of rest.DailyRatesService
     * @return an instance of java.lang.String
     */
    @GET
    @Path("dailyrates")
    @Produces("application/json")
    public String getRates() {
        return gson.toJson(facade.getAll());
    }
    
    @GET
    @Path("calculator/{amount}/{fromcurrency}/{tocurrency}")
    @Produces("application/json")
    public String calculate(@PathParam("amount") double amount, @PathParam("fromcurrency") String fromcurrency, @PathParam("tocurrency") String tocurrency) {
        return gson.toJson(facade.convert(amount, fromcurrency, tocurrency));
    }

}

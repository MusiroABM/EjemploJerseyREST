package resource;

import data.User;
import es.uji.belfern.generador.GeneradorDatosINE;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("api")
public class UserResource {
    private final GeneradorDatosINE generador = new GeneradorDatosINE();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hola() {
        return "Hola";
    }

    @GET @Path("aleatorio")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User aleatorio() {
        return new User(generador.getNombre(), generador.getNIF());
    }
}

package resource;

import data.User;
import data.Users;
import es.uji.belfern.generador.GeneradorDatosINE;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("api")
public class UserResource {
//    private final GeneradorDatosINE generador = new GeneradorDatosINE();
    private static final Users USERS = new Users();
    @Context
    private UriInfo uriInfo;

/*
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hola() {
        return "Hola";
    }
*/

/*
    @GET @Path("aleatorio")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response aleatorio() {
        User user = new User(generador.getNombre(), generador.getNIF(), generador.getApellido());


        Link userSelf = Link.fromUri(uriInfo.getAbsolutePath()
        .resolve(user.getId()))
                .rel("self")
                .type("GET")
                .build();

        user.setSelf(userSelf);

        return Response.ok(user)
                .links(userSelf)
                .build();
    }
*/

/*
    @GET @Path("/User")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUser() {
        return Response.ok(new User("Óscar", "Belmonte", "123"))
                .link(uriInfo.getBaseUri(), "this")
                .build();
    }
*/

    @GET @Path("/users/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("id") String id) {
        return Response.ok(USERS.getUser(id))
                .build();
    }

    @GET @Path("/users")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUsers(@DefaultValue("1") @QueryParam("page") int page, @DefaultValue("" + Users.MAX_USERS) @QueryParam("pageSize") int pageSize) {
        Link prev = getLinkPrevious(page, pageSize);
        Link next = getLinkNext(page, pageSize);

        Users Users = getUsersWithSelf(page, pageSize);

        return Response.ok(Users)
                .links(prev)
                .links(next)
                .build();
    }

/*
    @GET @Path("/coso")
    @Produces(MediaType.TEXT_PLAIN)
    public String hola() {
        return "Hola";
    }
*/

    private Users getUsersWithSelf(int page, int pageSize) {
        Users Users = USERS.getUsers(page, pageSize);
        for(User User: Users.getUsers())
            setUserSelf(User);

        return Users;
    }

    private Link getLinkPrevious(int page, int pageSize) {
        return Link.fromUri(uriInfo.getAbsolutePath())
                .param("page", ""+(page-1))
                .param("pageSize", ""+pageSize)
                .rel("prev")
                .type("GET")
                .build();
    }

    private Link getLinkNext(int page, int pageSize) {
        return Link.fromUri(uriInfo.getAbsolutePath())
                .param("page", ""+(page+1))
                .param("pageSize", ""+pageSize)
                .rel("next")
                .type("GET")
                .build();
    }

    private void setUserSelf(User User) {
        User.setSelf(Link
                .fromUri(uriInfo.getAbsolutePath().resolve(User.getId()))
                .rel("self")
                .type("GET")
                .build());
    }

}

package resource;

import data.User;
import data.Users;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("api")
public class UserResource {
    private static final Users USERS = new Users();
    @Context
    private UriInfo uriInfo;

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

        Users users = getUsersWithSelf(page, pageSize);

        return Response.ok(users)
                .links(prev)
                .links(next)
                .build();
    }

    private Users getUsersWithSelf(int page, int pageSize) {
        Users users = USERS.getUsers(page, pageSize);
        for(User user: users.getUsers())
            setUserSelf(user);

        return users;
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

    private void setUserSelf(User user) {
        user.setSelf(Link
                .fromUri(uriInfo.getAbsolutePath().resolve(user.getId()))
                .rel("self")
                .type("GET")
                .build());
    }

}

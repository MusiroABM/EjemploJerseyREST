package resource;

import data.User;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;

import static org.junit.jupiter.api.Assertions.*;

public class UserResourceTest extends JerseyTest {
    @Override
    protected Application configure() {
        return new ResourceConfig()
                .packages("resource");
    }

    @Test
    public void oscarTest() {
        User oscar = new User("Óscar", "Belmonte", "123");
        User retrieved = target("api/users/oscar")
                .request()
                .get(User.class);

        assertEquals(oscar, retrieved);
    }
}

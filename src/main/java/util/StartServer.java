package util;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class StartServer {
    public static final String BASE_URI = "http://localhost:8080/";

    public static Server startServer(){
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build();
        ResourceConfig config = new ResourceConfig()
                .packages("resource")
                .register(MoxyJsonFeature.class);

        Server server = JettyHttpContainerFactory.createServer(baseUri, config);

        return server;
    }

    public static void main(String[] args) {
        startServer();
    }
}

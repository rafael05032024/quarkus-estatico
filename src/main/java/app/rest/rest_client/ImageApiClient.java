package app.rest.rest_client;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import app.models.Image;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/v2")
@RegisterRestClient(configKey = "image-api")
public interface ImageApiClient {

    @GET
    @Path("/list")
    List<Image> list(@QueryParam("limit") int limit);
}

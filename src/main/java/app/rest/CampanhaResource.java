package app.rest;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import app.models.Image;
import app.rest.rest_client.ImageApiClient;
import app.services.ImageDownloaderService;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/cats")
public class CampanhaResource {

    @Inject
    @RestClient
    ImageApiClient imgApi;

    @Inject
    Template campanhas;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance renderCats() {
        List<Image> images = imgApi.list(5);
        int i = 0;

        for (Image elem : images) {
            String fileName = "image-" + i;

            System.err.println(elem.getDownload_url()
            );

            try {
                String localPath = ImageDownloaderService.downloadImage(elem.getDownload_url(), fileName
                );

                elem.setLocalPath(localPath);

                i++;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }

        return campanhas.data("cats", images);
    }
}

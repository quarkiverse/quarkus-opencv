package io.quarkiverse.opencv.test;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/opencv/live-reload")
public class OpenCVReloadedCodeTestResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public int cols() {
        return new OpenCVReloadedCode().cols();
    }
}

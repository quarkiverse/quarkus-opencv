package io.quarkiverse.opencv.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/opencv/live-reload")
public class OpenCVReloadedCodeTestResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public int cols() {
        return new OpenCVReloadedCode().cols();
    }
}

package io.quarkiverse.opencv.test;

import static org.hamcrest.Matchers.is;

import java.util.function.Function;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusDevModeTest;
import io.restassured.RestAssured;

public class OpenCVDevModeTest {

    @RegisterExtension
    static final QuarkusDevModeTest devModeTest = new QuarkusDevModeTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class)
                    .addClasses(OpenCVReloadedCode.class, OpenCVReloadedCodeTestResource.class));

    @Test
    public void writeYourOwnDevModeTest() {
        assertBodyIs("10");

        devModeTest.modifySourceFile(OpenCVReloadedCode.class, new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.replace("10, 10", "7, 7");
            }
        });

        assertBodyIs("7");
    }

    private void assertBodyIs(String expectedBody) {
        RestAssured.when().get("/opencv/live-reload").then().body(is(expectedBody));
    }
}

package io.quarkiverse.opencv.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class OpenCVResourceTest {

    @Test
    public void testBasic() {
        given()
                .when().get("/opencv/basic")
                .then()
                .statusCode(200)
                .body(is("2"));
    }

    @Test
    public void testDnn() {
        given()
                .when().get("/opencv/dnn")
                .then()
                .statusCode(200)
                .body(is("org.opencv.dnn.Net"));
    }
}

package io.quarkiverse.opencv.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import io.quarkus.test.QuarkusUnitTest;

public class OpenCVTest {

    @RegisterExtension
    static final QuarkusUnitTest unitTest = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class));

    @Test
    public void writeYourOwnUnitTest() {
        assertEquals(2, Mat.zeros(10, 10, CvType.CV_8UC1).dims());
    }
}

package io.quarkiverse.opencv.test;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class OpenCVReloadedCode {

    public int cols() {
        return Mat.zeros(10, 10, CvType.CV_8UC1).cols();
    }
}

package io.quarkiverse.opencv.runtime;

import io.quarkus.runtime.annotations.Recorder;
import nu.pattern.OpenCV;

@Recorder
public class OpenCVRecorder {

    public void loadOpenCVLibrary() {
        OpenCV.loadLocally();
    }
}

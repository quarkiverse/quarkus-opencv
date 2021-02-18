package io.quarkiverse.opencv.deployment;

import java.io.IOException;

import io.quarkiverse.opencv.deployment.OpenCVLibraryUtil.Arch;
import io.quarkiverse.opencv.deployment.OpenCVLibraryUtil.OS;
import io.quarkiverse.opencv.runtime.OpenCVRecorder;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBuildItem;
import io.quarkus.deployment.builditem.nativeimage.RuntimeInitializedClassBuildItem;
import io.quarkus.deployment.pkg.NativeConfig;

class OpenCVProcessor {

    private static final String FEATURE = "opencv";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    void nativeJni(BuildProducer<RuntimeInitializedClassBuildItem> runtimeInitializedClasses,
            BuildProducer<NativeImageResourceBuildItem> nativeImageResources,
            NativeConfig nativeConfig) throws IOException {
        if (nativeConfig.containerRuntime.isPresent() || nativeConfig.containerBuild) {
            nativeImageResources.produce(
                    new NativeImageResourceBuildItem(
                            OpenCVLibraryUtil.extractNativeBinary(OS.LINUX, Arch.X86_64).substring(1)));
        } else {
            nativeImageResources.produce(new NativeImageResourceBuildItem(
                    OpenCVLibraryUtil.extractNativeBinary(OS.getCurrent(), Arch.getCurrent()).substring(1)));
        }

        // We don't want to load OpenCV during build
        runtimeInitializedClasses.produce(new RuntimeInitializedClassBuildItem("nu.pattern.OpenCV"));
        runtimeInitializedClasses.produce(new RuntimeInitializedClassBuildItem("nu.pattern.OpenCV$SharedLoader"));
        runtimeInitializedClasses.produce(new RuntimeInitializedClassBuildItem("nu.pattern.OpenCV$LocalLoader"));
        runtimeInitializedClasses.produce(new RuntimeInitializedClassBuildItem("nu.pattern.OpenCV$LocalLoader$Holder"));
    }

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    void loadOpenCV(OpenCVRecorder recorder) {
        // Explicitly load OpenCV at application startup.
        recorder.loadOpenCVLibrary();
    }
}

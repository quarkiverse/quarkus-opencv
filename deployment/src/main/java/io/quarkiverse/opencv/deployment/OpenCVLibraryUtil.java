package io.quarkiverse.opencv.deployment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import nu.pattern.OpenCV;

/**
 * This code is extracted from the {@link OpenCV} class as most things are private there.
 */
public class OpenCVLibraryUtil {

    public static String extractNativeBinary(final OS os, final Arch arch) {
        final String location;

        switch (os) {
            case LINUX:
                switch (arch) {
                    case X86_64:
                        location = "/nu/pattern/opencv/linux/x86_64/libopencv_java430.so";
                        break;
                    case ARMv8:
                        location = "/nu/pattern/opencv/linux/ARMv8/libopencv_java430.so";
                        break;
                    default:
                        throw new UnsupportedPlatformException(os, arch);
                }
                break;
            case OSX:
                switch (arch) {
                    case X86_64:
                        location = "/nu/pattern/opencv/osx/x86_64/libopencv_java430.dylib";
                        break;
                    default:
                        throw new UnsupportedPlatformException(os, arch);
                }
                break;
            case WINDOWS:
                switch (arch) {
                    case X86_32:
                        location = "/nu/pattern/opencv/windows/x86_32/opencv_java430.dll";
                        break;
                    case X86_64:
                        location = "/nu/pattern/opencv/windows/x86_64/opencv_java430.dll";
                        break;
                    default:
                        throw new UnsupportedPlatformException(os, arch);
                }
                break;
            default:
                throw new UnsupportedPlatformException(os, arch);
        }

        return location;
    }

    static enum OS {
        OSX("^[Mm]ac OS X$"),
        LINUX("^[Ll]inux$"),
        WINDOWS("^[Ww]indows.*");

        private final Set<Pattern> patterns;

        private OS(final String... patterns) {
            this.patterns = new HashSet<Pattern>();

            for (final String pattern : patterns) {
                this.patterns.add(Pattern.compile(pattern));
            }
        }

        private boolean is(final String id) {
            for (final Pattern pattern : patterns) {
                if (pattern.matcher(id).matches()) {
                    return true;
                }
            }
            return false;
        }

        public static OS getCurrent() {
            final String osName = System.getProperty("os.name");

            for (final OS os : OS.values()) {
                if (os.is(osName)) {
                    return os;
                }
            }

            throw new UnsupportedOperationException(String.format("Operating system \"%s\" is not supported.", osName));
        }
    }

    static enum Arch {
        X86_32("i386", "i686", "x86"),
        X86_64("amd64", "x86_64"),
        ARMv8("arm");

        private final Set<String> patterns;

        private Arch(final String... patterns) {
            this.patterns = new HashSet<String>(Arrays.asList(patterns));
        }

        private boolean is(final String id) {
            return patterns.contains(id);
        }

        public static Arch getCurrent() {
            final String osArch = System.getProperty("os.arch");

            for (final Arch arch : Arch.values()) {
                if (arch.is(osArch)) {
                    return arch;
                }
            }

            throw new UnsupportedOperationException(String.format("Architecture \"%s\" is not supported.", osArch));
        }
    }

    private static class UnsupportedPlatformException extends RuntimeException {
        private UnsupportedPlatformException(final OS os, final Arch arch) {
            super(String.format("Operating system \"%s\" and architecture \"%s\" are not supported by OpenCV.", os, arch));
        }
    }
}

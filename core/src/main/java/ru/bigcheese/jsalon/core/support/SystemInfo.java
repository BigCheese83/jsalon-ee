package ru.bigcheese.jsalon.core.support;

/**
 * Created by BigCheese on 14.12.15.
 */
public class SystemInfo {

    private final String os;
    private final String osFamily;
    private final String javaVendor;
    private final String javaVendorUrl;
    private final String jre;
    private final String jvm;

    public SystemInfo() {
        this.os = System.getProperty("os.name") + " v." + System.getProperty("os.version") + " " + System.getProperty("os.arch");
        this.osFamily = OSValidator.detectOsFamily();
        this.javaVendor = System.getProperty("java.vendor");
        this.javaVendorUrl = System.getProperty("java.vendor.url");
        this.jre = System.getProperty("java.runtime.name") + " v." + System.getProperty("java.runtime.version");
        this.jvm = System.getProperty("java.vm.vendor") + " " + System.getProperty("java.vm.name") + " v." +
                System.getProperty("java.vm.version") + " " + System.getProperty("java.vm.info");
    }

    public String getOs() {
        return os;
    }

    public String getOsFamily() {
        return osFamily;
    }

    public String getJavaVendor() {
        return javaVendor;
    }

    public String getJavaVendorUrl() {
        return javaVendorUrl;
    }

    public String getJre() {
        return jre;
    }

    public String getJvm() {
        return jvm;
    }

    public String getJvmMemory() {
        return "Free: " + Runtime.getRuntime().freeMemory()/1048576L +
                "MB Total: " + Runtime.getRuntime().totalMemory()/1048576L +
                "MB Max: " + Runtime.getRuntime().maxMemory()/1048576L + "MB";
    }

    private static class OSValidator {
        private static String os = System.getProperty("os.name").toLowerCase();

        public static boolean isWindows() {
            return os.contains("win");
        }
        public static boolean isMac() {
            return os.contains("mac");
        }
        public static boolean isUnix() {
            return os.contains("nix") || os.contains("nux") || os.contains("aix") || os.contains("bsd");
        }
        public static boolean isLinux() {
            return os.contains("nux");
        }
        public static boolean isSolaris() {
            return os.contains("sunos");
        }

        public static String detectOsFamily() {
            String result = "unknown";
            if (OSValidator.isWindows()) {
                result = "windows";
            } else if (OSValidator.isMac()) {
                result = "macos";
            } else if (OSValidator.isLinux()) {
                result = "linux";
            } else if (OSValidator.isUnix()) {
                result = "unix";
            } else if (OSValidator.isSolaris()) {
                result = "sunos";
            }
            return result;
        }
    }
}

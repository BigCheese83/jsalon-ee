package ru.bigcheese.jsalon.core.support;

/**
 * Created by BigCheese on 14.12.15.
 */
public class SystemInfo {

    public String getOs() {
        return System.getProperty("os.name") + " v." + System.getProperty("os.version") + " " + System.getProperty("os.arch");
    }

    public String getJavaVendor() {
        return System.getProperty("java.vendor");
    }

    public String getJavaVendorUrl() {
        return System.getProperty("java.vendor.url");
    }

    public String getJre() {
        return System.getProperty("java.runtime.name") + " v." + System.getProperty("java.runtime.version");
    }

    public String getJvm() {
        return System.getProperty("java.vm.vendor") + " " +
                System.getProperty("java.vm.name") + " v." +
                System.getProperty("java.vm.version") + " " +
                System.getProperty("java.vm.info");
    }

    public String getJvmMemory() {
        return "Free: " + Runtime.getRuntime().freeMemory()/1048576L +
                "MB Total: " + Runtime.getRuntime().totalMemory()/1048576L +
                "MB Max: " + Runtime.getRuntime().maxMemory()/1048576L + "MB";
    }
}

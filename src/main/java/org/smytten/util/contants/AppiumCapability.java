package org.smytten.util.contants;

public enum AppiumCapability {
    DEVICE_NAME("deviceName"),
    PLATFORM_VERSION("platformVersion"),
    PLATFORM_NAME("platformName"),
    AUTOMATION_NAME("automationName"),
    AUTO_GRANT_PERMISSIONS("autoGrantPermissions"),
    ENFORCE_APP_INSTALL("enforceAppInstall"),
    APP("app");

    private final String capabilityName;

    AppiumCapability(String capabilityName) {
        this.capabilityName = capabilityName;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

}

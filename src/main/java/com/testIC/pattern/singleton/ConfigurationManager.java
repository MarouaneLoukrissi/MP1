package com.testIC.pattern.singleton;

public class ConfigurationManager {

    private static volatile ConfigurationManager instance;

    private final String applicationName;
    private String environment;
    private final boolean notificationsEnabled;
    private ConfigurationManager() {
        this.applicationName = "Finance Application";
        this.environment = "PRODUCTION";
        this.notificationsEnabled = true;
    }
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }
    public String getApplicationName() {
        return applicationName;
    }
    public String getEnvironment() {
        return environment;
    }
    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }
    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
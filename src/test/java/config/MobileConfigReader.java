package config;

import org.aeonbits.owner.ConfigFactory;

public class MobileConfigReader {

    public static LocalMobileConfig getLocalConfig() {
        return ConfigFactory.create(LocalMobileConfig.class);
    }

    public static RemoteMobileConfig getRemoteConfig() {
        return ConfigFactory.create(RemoteMobileConfig.class);
    }

    public static boolean isRemote() {
        String env = System.getProperty("env", "remote");

        return env.equalsIgnoreCase("remote");
    }
}
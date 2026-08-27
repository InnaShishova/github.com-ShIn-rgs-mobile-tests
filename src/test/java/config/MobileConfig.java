package config;

import org.aeonbits.owner.Config;

public interface MobileConfig extends Config {

    String device();

    String osVersion();

    String app();
}
package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/browserstack.properties"
})
public interface MobileConfig extends Config {

    @Key("device")
    String device();

    @Key("osVersion")
    String osVersion();

    @Key("app")
    String app();
}
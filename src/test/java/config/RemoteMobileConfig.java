package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/browserstack.properties"
})
public interface RemoteMobileConfig extends MobileConfig {

    @Override
    @Config.Key("device")
    String device();

    @Override
    @Config.Key("osVersion")
    String osVersion();

    @Override
    @Config.Key("app")
    String app();

    @Config.Key("remoteUrl")
    String remoteUrl();
}
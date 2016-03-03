package uno.cod.platform.server.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import uno.cod.platform.server.core.Profiles;
import uno.cod.storage.PlatformStorage;
import uno.cod.storage.gcs.GcsStorageDriver;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    //TODO: revise the developement implementation
    @Bean
    @Profile({Profiles.APPENGINE, Profiles.DEVELOPMENT})
    public PlatformStorage platformStorage() throws GeneralSecurityException, IOException {
        return new GcsStorageDriver(
                storageProperties.getGcs().getAccountId(),
                new File(storageProperties.getGcs().getPkcs12()));
    }


}

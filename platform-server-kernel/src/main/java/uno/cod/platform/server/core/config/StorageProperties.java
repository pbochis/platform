package uno.cod.platform.server.core.config;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = StorageProperties.PREFIX)
public class StorageProperties {
    public final static String PREFIX = "coduno.storage";
    private Gcs gcs;

    public Gcs getGcs() {
        return gcs;
    }

    public void setGcs(Gcs gcs) {
        this.gcs = gcs;
    }

    public static class Gcs {
        @NotBlank
        private String accountId;
        @NotBlank
        private String pkcs12;

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getPkcs12() {
            return pkcs12;
        }

        public void setPkcs12(String pkcs12) {
            this.pkcs12 = pkcs12;
        }
    }
}

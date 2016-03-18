package uno.cod.platform.server.core.util;

import java.util.concurrent.atomic.AtomicBoolean;

public class ServerUtil {
    private static final AtomicBoolean applicationInitialized = new AtomicBoolean(false);

    public static boolean isApplicationInitialized() {
        return applicationInitialized.get();
    }

    public static void setApplicationInitialized(boolean value) {
        applicationInitialized.set(value);
    }
}

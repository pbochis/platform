package uno.cod.platform.server.core.util;

import java.util.concurrent.atomic.AtomicBoolean;

public class ServerUtil {
    private static final AtomicBoolean APPLICATION_INITIALIZED = new AtomicBoolean(false);

    public static boolean getApplicationInitialized() {
        return APPLICATION_INITIALIZED.get();
    }

    public static void setApplicationInitialized(boolean value) {
        APPLICATION_INITIALIZED.set(value);
    }
}

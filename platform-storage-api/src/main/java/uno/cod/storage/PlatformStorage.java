package uno.cod.storage;

import java.io.IOException;
import java.util.List;

public interface PlatformStorage {
    List<String> listFiles(String path) throws IOException;
}

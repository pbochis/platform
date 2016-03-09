package uno.cod.platform.server.core.service;

import org.springframework.core.io.ByteArrayResource;

/**
 * Created by vbalan on 2/22/2016.
 */
public class FileMessageResource extends ByteArrayResource {
    private final String filename;

    public FileMessageResource(byte[] byteArray, String filename) {
        super(byteArray);
        this.filename = filename;
    }

    @Override
    public String getFilename() {
        return filename;
    }
}

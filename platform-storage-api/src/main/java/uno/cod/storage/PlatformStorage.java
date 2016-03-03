package uno.cod.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface PlatformStorage {
    List<String> listFiles(String bucket, String path) throws IOException;

    void upload(String bucket, String fileName, InputStream data, String contentType) throws IOException;

    InputStream download(String bucketName, String objectName) throws IOException;

    void downloadToOutputStream(String bucketName, String objectName, OutputStream data) throws IOException;

    String exposeFile(String bucket, String fileName, Long expiration) throws GeneralSecurityException, UnsupportedEncodingException;
}


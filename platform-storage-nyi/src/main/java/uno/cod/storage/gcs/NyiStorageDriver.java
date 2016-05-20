package uno.cod.storage.gcs;


import uno.cod.storage.PlatformStorage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

public class NyiStorageDriver implements PlatformStorage {

    @Override
    public List<String> listFiles(String bucket, String path) throws IOException {
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public void upload(String bucket, String fileName, InputStream data, String contentType) throws IOException {
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public InputStream download(String bucketName, String objectName) throws IOException {
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public void downloadToOutputStream(String bucketName, String objectName, OutputStream data) throws IOException {
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public String exposeFile(String bucket, String fileName, Long expiration) throws GeneralSecurityException, UnsupportedEncodingException {
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public List<String> exposeFilesInFolder(String bucket, String folderName, Long expiration) throws GeneralSecurityException, IOException {
        throw new UnsupportedOperationException("NYI");
    }

    @Override
    public String uploadPublic(String bucket, String fileName, InputStream data, String contentType) throws IOException {
        throw new UnsupportedOperationException("NYI");
    }
}

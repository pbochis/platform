package uno.cod.storage.gcs;


import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.StorageScopes;
import com.google.api.services.storage.model.Objects;
import com.google.api.services.storage.model.StorageObject;
import com.google.common.collect.Lists;
import uno.cod.storage.PlatformStorage;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.LinkedList;
import java.util.List;

public class GcsStorageDriver implements PlatformStorage {
    private String bucket;
    private Storage storage;

    public GcsStorageDriver(String accountId, File p12key, String bucket) throws GeneralSecurityException, IOException {
        GoogleCredential credential = new GoogleCredential.Builder().
                setTransport(new NetHttpTransport()).
                setJsonFactory(new JacksonFactory()).
                setServiceAccountId(accountId).
                setServiceAccountScopes(Lists.newArrayList(StorageScopes.DEVSTORAGE_FULL_CONTROL)).
                setServiceAccountPrivateKeyFromP12File(p12key).
                build();

        storage = new Storage.Builder(
                new NetHttpTransport(),
                new JacksonFactory(), credential).
                setApplicationName("platform").build();

        this.bucket = bucket;
    }

    @Override
    public List<String> listFiles(String path) throws IOException {
        List<String> allItems = new LinkedList<String>();
        Objects response = storage.objects().list(bucket).
                setPrefix(path).execute();
        for (StorageObject obj: response.getItems()) {
            allItems.add(obj.getName());
        }
        while (response.getNextPageToken() != null) {
            String pageToken = response.getNextPageToken();
            response = storage.objects().list(bucket).
                    setPrefix(path).setPageToken(pageToken).execute();
            for (StorageObject obj: response.getItems()) {
                allItems.add(obj.getName());
            }
        }
        return allItems;
    }
}

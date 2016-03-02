package uno.cod.platform.server.core.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Paul on 3/2/2016.
 */
@Entity
public class Template extends IdentifiableEntity implements StoredObject{
    public static final String BUCKET = "coduno-templates";

    private Language language;

    private String fileName;

    @ManyToOne
    private Task task;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String filePath() {
        return fileName;
    }
}

package uno.cod.platform.server.core.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A submission for a (task, challenge, user)
 */
@Entity
@Table(name = "submission")
public class Submission extends IdentifiableEntity implements StoredObject {
    @ManyToOne
    private Result result;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_id", nullable = false, updatable = false)
    private Task task;

    private String fileName;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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
        return getId() + "/" + fileName;
    }
}

package uno.cod.platform.server.core.domain;

import javax.persistence.*;
import java.util.List;

/**
 * A submission for a (task, challenge, user)
 */
@Entity
@Table(name = "submission")
public class Submission extends IdentifiableEntity implements StoredObject {
    @ManyToOne
    private Result result;

    @OneToMany(mappedBy = "submission")
    private List<TestResult> testResults;

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

    public List<TestResult> getTestResults() {
        return testResults;
    }

    public void setTestResults(List<TestResult> testResults) {
        this.testResults = testResults;
    }
}

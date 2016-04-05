package uno.cod.platform.server.codingcontest.sync.dto;

public class ContestResultDto {
    private int level;
    private int failedTests;
    private Long finishTime;
    private boolean codeUploaded;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getFailedTests() {
        return failedTests;
    }

    public void setFailedTests(int failedTests) {
        this.failedTests = failedTests;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    public boolean isCodeUploaded() {
        return codeUploaded;
    }

    public void setCodeUploaded(boolean codeUploaded) {
        this.codeUploaded = codeUploaded;
    }
}

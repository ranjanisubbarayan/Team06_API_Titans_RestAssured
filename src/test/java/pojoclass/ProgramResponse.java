package pojoclass;

public class ProgramResponse {

    private int programId;
    private String programName;
    private String programDescription;
    private String programStatus;
    private String creationTime;
    private String lastModTime;

    public int getProgramId() {
        return programId;
    }

    public String getProgramName() {
        return programName;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public String getProgramStatus() {
        return programStatus;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getLastModTime() {
        return lastModTime;
    }
}
package pojoclass;

public class BatchDataPojo {
    private String batchDescription;
    private String batchName;
    private int batchNoOfClasses;
    private String batchStatus;
    private Integer programId;
    private String programName;
    
    public String getBatchDescription() {
        return batchDescription;
    }
    public void setBatchDescription(String batchDescription) {
        this.batchDescription = batchDescription;
    }
    
    public String getBatchName() {
        return batchName;
    }
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
    
    public String getBatchStatus() {
        return batchStatus;
    }
    public void setBatchStatus(String batchStatus) {
        this.batchStatus = batchStatus;
    }
    
    public int getbatchNoOfClasses() {
        return batchNoOfClasses;
    }
    public void setbatchNoOfClasses(int batchNoOfClasses) {
        this.batchNoOfClasses = batchNoOfClasses;
    }
    
    public Integer getProgramId() {
        return programId;
    }
    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }
    public void setProgramName(String programName) {
        this.programName = programName;
    }
    
}

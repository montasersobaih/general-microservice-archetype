package ${package}.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class SubmissionInfo implements Submission {

    private String createdBy;

    private LocalDateTime creationDate;

    private String lastUpdatedBy;

    private LocalDateTime lastUpdatedDate;

    private boolean obsoletedFlag;

    private boolean expired;

    public SubmissionInfo(SubmissionInfo submissionInfo) {
        this.createdBy = submissionInfo.getCreatedBy();
        this.creationDate = submissionInfo.getCreationDate();
        this.lastUpdatedBy = submissionInfo.getLastUpdatedBy();
        this.lastUpdatedDate = submissionInfo.getLastUpdatedDate();
        this.obsoletedFlag = submissionInfo.isObsoletedFlag();
        this.expired = submissionInfo.isExpired();
    }

    public SubmissionInfo(String createdBy, LocalDateTime creationDate) {
        this.createdBy = createdBy;
        this.creationDate = creationDate;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Override
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    @Override
    public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public boolean isObsoletedFlag() {
        return obsoletedFlag;
    }

    @Override
    public void setObsoletedFlag(boolean obsoletedFlag) {
        this.obsoletedFlag = obsoletedFlag;
    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    @Override
    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}

package com.cobble.takeaway.pojo;

import java.util.Date;

public class SequencePOJO extends BasePOJO {
    private Long sequenceId;
    private String sequenceKey;
    private Long sequenceValue;
    private Date createDateTime;
    private Date lastModifiedDateTime;
	public Long getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(Long sequenceId) {
		this.sequenceId = sequenceId;
	}
	public String getSequenceKey() {
		return sequenceKey;
	}
	public void setSequenceKey(String sequenceKey) {
		this.sequenceKey = sequenceKey;
	}
	public Long getSequenceValue() {
		return sequenceValue;
	}
	public void setSequenceValue(Long sequenceValue) {
		this.sequenceValue = sequenceValue;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}
	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}
    
}
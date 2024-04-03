package com.thbs.BatchManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.ElementCollection;
import java.util.Date;
import java.util.List;

@Entity  
@Table(name="Batch", uniqueConstraints = @UniqueConstraint(columnNames = "batchName"))
public class Batch {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long Id;
	
	private String batchName;
	
	private Integer duration;
	
	private Date startDate;
	
	private Date endDate;
	
	@ElementCollection
	private List<Integer> traineesList;
	
	private Integer batchSize;
			
	public Batch() {
		super();
	}
	
	public Batch(Long id, String batchName, Integer duration, Date startDate, Date endDate, List<Integer> traineesList,
			Integer batchSize) {
		super();
		Id = id;
		this.batchName = batchName;
		this.duration = duration;
		this.startDate = startDate;
		this.endDate = endDate;
		this.traineesList = traineesList;
		this.batchSize = batchSize;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Integer> getTraineesList() {
		return traineesList;
	}

	public void setTraineesList(List<Integer> traineesList) {
		this.traineesList = traineesList;
	}

	public Integer getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(Integer batchSize) {
		this.batchSize = batchSize;
	}
	
	
}

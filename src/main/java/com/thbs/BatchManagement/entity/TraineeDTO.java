package com.thbs.BatchManagement.entity;



public class TraineeDTO {
	    
	    
	    
	    private Integer traineesList;
	    
	    // Constructors, getters, and setters...

	    public TraineeDTO() {
	        super();
	    }

//		public Long getBatchId() {
//			return batchId;
//		}
//
//		public void setBatchId(Long batchId) {
//			this.batchId = batchId;
//		}

		public Integer getTraineesList() {
			return traineesList;
		}

		public void setTraineesList(Integer traineesList) {
			this.traineesList = traineesList;
		}

		public TraineeDTO(Integer traineesList) {
			super();
			//this.batchId = batchId;
			this.traineesList = traineesList;
		}
	    
	    
}


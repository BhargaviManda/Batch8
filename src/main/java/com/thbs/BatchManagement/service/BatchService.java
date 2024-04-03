package com.thbs.BatchManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.thbs.BatchManagement.entity.Batch;
import com.thbs.BatchManagement.entity.TraineeDTO;
import com.thbs.BatchManagement.repository.BatchRepository;

import java.io.IOException;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;
    
    public int createBatch(Batch batch) {
        Batch existingBatch = batchRepository.findByBatchName(batch.getBatchName());
        if (existingBatch == null) {
            batchRepository.save(batch);
            return 1; // Created
        }
        return 0; // Already exists
    }
    
//    public void addTraineeToBatch(TraineeDTO trainee) {
//        Batch batch = batchRepository.findById(trainee.getBatchId()).orElse(null);
//        if (batch != null) {
//            // Assuming trainee is not null and batch exists
//            batch.getTraineesList().add(trainee.getEmployeeId());
//            batchRepository.save(batch);
//        } else {
//            throw new RuntimeException("Batch not found");
//        }
//    }
//    
    // Implement this method to add a list of trainees to a batch
    public void addTraineesToBatch(Batch batch, List<TraineeDTO> trainees) {
        // Implement the logic to add multiple trainees to the batch
        // You can loop through the trainees list and add each trainee to the batch
        for (TraineeDTO trainee : trainees) {
            batch.getTraineesList().add(trainee.getTraineesList());
        }
        batchRepository.save(batch);
    }
    

    public void addTrainees(List<TraineeDTO> trainees, String data) {
        // Extract trainee IDs from TraineeDTO objects
    	
        List<Integer> traineeIds = trainees.stream()
                                           .map(TraineeDTO::getTraineesList)
                                           .collect(Collectors.toList());
  
        // Create a Batch entity and set the trainee IDs
        Batch batch = new Batch();
        batch.setTraineesList(traineeIds);

        // Save the batch to the database
        try {
            // Parse data as JSON object
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(data);

            // Set fields of Batch entity from JSON object
            if (jsonNode.has("batchName"))
                batch.setBatchName(jsonNode.get("batchName").asText());
            if (jsonNode.has("duration"))
                batch.setDuration(jsonNode.get("duration").asInt());
            if (jsonNode.has("startDate")) {
                // Parse startDate string to Date
                String startDateStr = jsonNode.get("startDate").asText();
                Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
                batch.setStartDate(startDate);
            }
            if (jsonNode.has("endDate")) {
                // Parse endDate string to Date
                String endDateStr = jsonNode.get("endDate").asText();
                Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
                batch.setEndDate(endDate);
            }
//            if (jsonNode.has("startDate"))
//                batch.setStartDate(LocalDate.parse(jsonNode.get("startDate").asText()));
//            if (jsonNode.has("endDate"))
//                
//            Date endDate = LocalDate.parse(jsonNode.get("endDate").asText());
//            batch.setEndDate(Date.valueOf(endDate));
            
            if (jsonNode.has("batchSize"))
                batch.setBatchSize(jsonNode.get("batchSize").asInt());

            // You can add more fields similarly based on your Batch entity structure

        } catch (IOException e) {
            // Handle exception if JSON parsing fails
            e.printStackTrace(); // Or handle it according to your application's requirements
        }
        catch (ParseException e) {
            // Handle exception if date parsing fails
            e.printStackTrace(); // Or handle it according to your application's requirements
        }

        batchRepository.save(batch);
    }
}

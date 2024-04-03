package com.thbs.BatchManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.thbs.BatchManagement.entity.Batch;
import com.thbs.BatchManagement.entity.TraineeDTO;
import com.thbs.BatchManagement.service.BatchService;
import com.thbs.BatchManagement.service.ExcelService;

import java.io.IOException;
import java.util.List;

@RestController
public class BatchController {

    @Autowired
    private BatchService batchService;

    @Autowired
    private ExcelService excelService;

    @PostMapping("/createbatch")
    public ResponseEntity<String> createBatch(@RequestBody Batch batch) {
        int result = batchService.createBatch(batch);
        if (result == 1) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Batch created successfully");
        } else if (result == 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Batch already exists");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid batch details");
        }
    }

//    @PostMapping("/addTrainee")
//    public ResponseEntity<String> addTrainee(@RequestBody TraineeDTO trainee) {
//        try {
//            batchService.addTraineeToBatch(trainee);
//            return ResponseEntity.status(HttpStatus.OK).body("Trainee added successfully");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding trainee");
//        }
//    }

    @PostMapping("/bulkUpload")
    public ResponseEntity<String> bulkUpload(@RequestParam("file") MultipartFile file, @RequestParam("data") String data) {
        try {
            List<TraineeDTO> trainees = excelService.parseExcel(file);
            System.out.println(data);
            // Implementation to save trainees to batch...
            // Example: batchService.addTrainees(trainees);
            batchService.addTrainees(trainees,data);
            return ResponseEntity.status(HttpStatus.OK).body("Trainees uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading trainees");
        }
    }
}

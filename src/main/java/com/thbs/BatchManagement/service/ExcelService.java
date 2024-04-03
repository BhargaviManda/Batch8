package com.thbs.BatchManagement.service;



import com.thbs.BatchManagement.entity.TraineeDTO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelService {

    public List<TraineeDTO> parseExcel(MultipartFile file) throws IOException {
        List<TraineeDTO> trainees = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            TraineeDTO trainee = new TraineeDTO();
            Cell cell = row.getCell(0); // Assuming trainee ID is in the first column

            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                trainee.setTraineesList((int) cell.getNumericCellValue());
                // Set other trainee details as needed from other columns
                // Example: trainee.setName(row.getCell(1).getStringCellValue());
                trainees.add(trainee);
            }
        }
        workbook.close();
        return trainees;
    }
}

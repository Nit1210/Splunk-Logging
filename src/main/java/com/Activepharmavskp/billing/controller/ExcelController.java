package com.Activepharmavskp.billing.controller;

import com.Activepharmavskp.billing.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        if (!file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type. Please upload an Excel file.");
        }

        try {
            excelService.saveProductsFromExcel(file);
            return ResponseEntity.ok("File uploaded and data saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process file: " + e.getMessage());
        }
    }
}
package com.Activepharmavskp.billing.service;
import com.Activepharmavskp.billing.model.Product;
import com.Activepharmavskp.billing.repository.ProductRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
@Service
public class ExcelService {


    @Autowired
    private ProductRepository productRepository;

    public void saveProductsFromExcel(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            List<Product> products = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Start from row 1 to skip headers
                Row row = sheet.getRow(i);

                Product product = new Product();
                product.setCategory(row.getCell(0).getStringCellValue());
                product.setName(row.getCell(1).getStringCellValue());
                product.setContains(row.getCell(2).getStringCellValue());
                product.setPack(row.getCell(3).getStringCellValue());
                product.setBillRate(row.getCell(4).getNumericCellValue());
                product.setMrp(row.getCell(5).getNumericCellValue());
                product.setRealizedRate10(row.getCell(6).getNumericCellValue());
                product.setRealizedRate15(row.getCell(7).getNumericCellValue());

                products.add(product);
            }

            productRepository.saveAll(products);
        } catch (Exception e) {
            throw new RuntimeException("Failed to process Excel file", e);
        }
    }
}

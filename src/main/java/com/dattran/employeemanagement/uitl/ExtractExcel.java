package com.dattran.employeemanagement.uitl;

import com.dattran.employeemanagement.database.ProductSystem;
import com.dattran.employeemanagement.model.ProductSystemObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ExtractExcel {
    public boolean extractExcel() {
        // Khởi tạo workbook cho tệp xlsx
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Khởi tạo một worksheet mới từ workbook
        XSSFSheet sheet = workbook.createSheet("Student Details");
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[]{"ID", "NAME", "MANAGER ID", "NOTES", "PS_DELETED"
                ,"CREATED_DATE", "MODIFIED_DATE", "DELETED_DATE", "DELETED_AUTHOR", "PS_TABLE",
                "ENABLE", "PS_NAME_EN", "CREATED_AUTHOR_ID", "LANGUAGE"});
        ProductSystem productSystem = new ProductSystem();
        List<ProductSystemObject> list = productSystem.getAllProductsByList();
        for (int i=0; i<list.size();i++) {
            ProductSystemObject product = list.get(i);
            data.put(String.valueOf(i+2), new Object[]{
                    product.getProductSystem_id(), product.getProductSystem_name(),
                    product.getProductSystem_manager_id(), product.getProductSystem_notes(),
                    product.getProductSystem_delete(), product.getProductSystem_created_date(),
                    product.getProductSystem_modified_date(), product.getProductSystem_deleted_date(),
                    product.getProductSystem_deleted_author(), product.getProductSystem_table(),
                    product.getProductSystem_enable(),product.getProductSystem_name_en(), product.getProductSystem_created_author_id(),
                    product.getProductSystem_language()
            });
        }
        // Duyệt và thêm dữ liệu từng row
        Set<String> keySet = data.keySet();
        int rowNum = 0;
        for (String key : keySet) {
            Row row = sheet.createRow(rowNum++);
            Object[] object = data.get(key);
            int cellNum = 0;
            for (Object obj : object) {
                Cell cell = row.createCell(cellNum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer){
                    cell.setCellValue((Integer) obj);
                }
            }
        }
        // ghi dữ liệu xuống file
        try {
            String path = new File("src/main/resources/file/excel.xlsx").getAbsolutePath();
            //File file = new File("E:/Java/JavaFx/EmployeeManagement/src/main/resources/file/excel.xlsx");
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        ExtractExcel excel = new ExtractExcel();
        excel.extractExcel();
    }
}

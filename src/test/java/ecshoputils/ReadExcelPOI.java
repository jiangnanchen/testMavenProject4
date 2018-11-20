package ecshoputils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * tedu.cn
 * @author zhengxj
 *
 */
public class ReadExcelPOI {


		public static Object[][] getTestData(String filePath, String fileName,
				String sheetName){
			Workbook workbook = null;
			
			try{
				File file = new File(filePath + "\\" + fileName);
				FileInputStream inputStream = new FileInputStream(file);
	
				String fileExtensionName = fileName.substring(fileName.indexOf("."));
	
				if (fileExtensionName.equals(".xlsx")) {
					workbook = new XSSFWorkbook(inputStream);
				} else if (fileExtensionName.equals(".xls")) {
					workbook = new HSSFWorkbook(inputStream);
				}
			} catch (IOException e){
				System.out.println(e.getMessage());
				return null;
			}

			Sheet Sheet = workbook.getSheet(sheetName);
			int rowCount = Sheet.getLastRowNum() - Sheet.getFirstRowNum();
			int colCount = Sheet.getRow(0).getLastCellNum();
			List<Object[]> records = new ArrayList<Object[]>();
			for (int i = 1; i < rowCount + 1; i++) {
				Row row = Sheet.getRow(i);
				String fields[] = new String[colCount];
				for (int j = 0; j < colCount; j++) {
					Cell cell=row.getCell(j);
					if (j>=row.getLastCellNum() || cell==null){
						fields[j] = "";
					}else{
						cell.setCellType(Cell.CELL_TYPE_STRING);
						fields[j] = cell.getStringCellValue();
					}
				}
				records.add(fields);
			}

			Object[][] results = new Object[records.size()][];
			for (int i = 0; i < records.size(); i++) {
				results[i] = records.get(i);
				System.out.println(results[i]);
			}
			return results;
		}
}

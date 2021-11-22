package com.kimjaeeun.sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kimjaeeun.domain.BoardVo;
import com.kimjaeeun.domain.Criteria;
import com.kimjaeeun.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class Sample2Tests {
	@Setter @Autowired
	private BoardMapper mapper;
	@Test
	public void tetGetListPaging() {
		Criteria cri =  new Criteria(); 
		mapper.getListWithPaging(cri).forEach(log::info);
		//글번호 제목 작성자 작성일
		
	}
	
	@Test
	public void testCreateExcelByBoard() throws IOException{
		List<BoardVo> list = mapper.getListWithPaging(new Criteria());
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet= workbook.createSheet("board Sheet");
		
		Cell cell = getCell(sheet,0,0);
		cell.setCellValue("글번호");
		
		cell = getCell(sheet,0,1);
		cell.setCellValue("제목");
		
		cell = getCell(sheet,0,2);
		cell.setCellValue("작성자");
		
		cell = getCell(sheet,0,3);
		cell.setCellValue("작성일");
		
		for(int i=0;i<list.size();i++){
		cell = getCell(sheet,i+1,0);
		cell.setCellValue(list.get(i).getBno());
		cell = getCell(sheet,i+1,1);
		cell.setCellValue(list.get(i).getTitle());
		cell = getCell(sheet,i+1,2);
		cell.setCellValue(list.get(i).getWriter());
		cell = getCell(sheet,i+1,3);
		cell.setCellValue(list.get(i).getRegDate());
		CellStyle style=workbook.createCellStyle();
		style.setDataFormat(workbook.createDataFormat().getFormat("yy/MM/dd hh:mm"));
		cell.setCellStyle(style);
		}
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		FileOutputStream stream = new FileOutputStream("C:\\Users\\kje\\Documents\\board.xlsx");
		workbook.write(stream);
	}
	//엑셀 가져오기
	@Test
	public void testReadExcel() throws IOException{
		FileInputStream stream = new FileInputStream("C:\\Users\\kje\\Documents\\score.xlsx");
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheetAt(0);
		List<String> mapNames = new ArrayList<>();
		for(int i = 0; ;i++){
			String name=getCell(sheet,0,i).getStringCellValue();
			if(name.equals("")) break;
			mapNames.add(name);
		}
		log.info(mapNames);
		
		List<Map<String,Object>> list= new ArrayList<>();
		for(int i = 0; ; i++){
			Map<String,Object> map = new HashMap<>();
			if(getCell(sheet,i+1,0).getCellType()==CellType.BLANK)break;
		for(int j = 0 ; j < mapNames.size(); j++){
			CellType type= getCell(sheet,i+1,j).getCellType();
			switch(type){
			case NUMERIC: case FORMULA:
				map.put(mapNames.get(j), (int)getCell(sheet, i+1,j).getNumericCellValue());
				break;
			case STRING:
				map.put(mapNames.get(j), getCell(sheet, i+1,j).getStringCellValue());
				break;
			default:
				break;
			}
		}
		list.add(map);
		}
		list.forEach(log::info);
	}
		
	
	
	@Test
	public void testCreateExcel() throws IOException{
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet= workbook.createSheet("TEST Sheet");
		
		Cell cell = getCell(sheet,0,0);
		cell.setCellValue("Test Value");
		
		cell=getCell(sheet, 0,1);
		cell.setCellValue(100);
		
		cell=getCell(sheet,0,2);
		cell.setCellValue(Calendar.getInstance());
		
		//1행 A,B,C
		//style
		CellStyle style=workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("yy/MM/dd hh:mm"));
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.TOP);
		style.setFillBackgroundColor(IndexedColors.BLUE.index);
		Font font = workbook.createFont();
		font.setColor(IndexedColors.WHITE.index);
		cell.setCellStyle(style);

		//셀 너비
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		
		cell=getCell(sheet, 1,0);
		cell.setCellValue(1);
		cell=getCell(sheet,1,1);
		cell.setCellValue(2);
		cell=getCell(sheet,1,2);
		cell.setCellFormula("SUM(A2:B2)");
		
		FileOutputStream stream = new FileOutputStream("C:\\Users\\kje\\Documents\\test.xlsx");
		workbook.write(stream);
	}
	
	//row취득
	public Row getRow(Sheet sheet, int rownum){
		Row row = sheet.getRow(rownum);
		if(row == null){
			row = sheet.createRow(rownum);
		}
		return row;
	}
	//cell 취득
	public Cell getCell(Row row, int cellnum){
		Cell cell= row.getCell(cellnum);
		if(cell==null){
			cell=row.createCell(cellnum);
		}
		return cell;
	}
	public Cell getCell(Sheet sheet,  int rownum, int cellnum){
		Row row = getRow(sheet, rownum);
		return getCell(row,cellnum); 
	}
}

package br.ufes.inf.nemo.antipattern;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.file.TimeHelper;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLModelStatistic;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLModelStatistic.InfoType;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLModelStatistic.LineType;

public class WorksheetGenerator {

	private static final String XLSX = ".xlsx";
	private static final String OUTPUT_FILE = "Structure"+XLSX;
	private static final String REFONTO = ".refontouml";
	
	private final String DIR;
	private ArrayList<String> textFiles = new ArrayList<String>();
	private XSSFWorkbook workbook = new XSSFWorkbook();
	
	public WorksheetGenerator(String DIR) {
		this.DIR = DIR;
	}
	
	public void run(){
		loadFileNamesInDirectory();
		createSheets();
		
		for (int i = 0; i < textFiles.size(); i++) {
			
			OntoUMLModelStatistic diagnostic = createDiagnostic(DIR+textFiles.get(i));
			if(diagnostic==null)
				continue;
			
			diagnostic.run();
			int j = 0;
			for (LineType line : LineType.values()) {
				XSSFSheet countSheet = workbook.getSheetAt(j);
				XSSFSheet typeSheet = workbook.getSheetAt(j+1);
				XSSFSheet allSheet = workbook.getSheetAt(j+2);
				
				if(i==0){				
					copyListToRow(countSheet.createRow(0), diagnostic.createList(line, InfoType.MEASURE, true));
					copyListToRow(typeSheet.createRow(0), diagnostic.createList(line, InfoType.MEASURE, true));
					copyListToRow(allSheet.createRow(0), diagnostic.createList(line, InfoType.MEASURE, true));
				}
				
				copyListToRow(countSheet.createRow(i+1), diagnostic.createList(line, InfoType.COUNT, true));
				copyListToRow(typeSheet.createRow(i+1), diagnostic.createList(line, InfoType.TYPE_PERCENTAGE, true));
				copyListToRow(allSheet.createRow(i+1), diagnostic.createList(line, InfoType.ALL_PERCENTAGE, true));
				
				j+=3;
			}	
		}
		
		autoSizeAllColumns();
	}
	
	private void autoSizeAllColumns(){
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			for (int j = 0; j < 20; j++) {
				workbook.getSheetAt(i).autoSizeColumn(j);
			}
			
		}
	}

	private void copyListToRow(XSSFRow row, ArrayList<Object> list) {
		int c = 0;
		for (Object o : list) {
			XSSFCell cell = row.createCell(c);
			
			if(o instanceof Integer){
				cell.setCellValue((int)o);
			}
			else if(o instanceof Float){
				cell.setCellValue((float)o);
			}
			else if(o instanceof String)
				cell.setCellValue((String)o);
			
			if(c>0){
				XSSFCellStyle style = workbook.createCellStyle();
				style.setAlignment(CellStyle.ALIGN_CENTER);	
				
				if(o instanceof Float){
					style.setDataFormat(workbook.createDataFormat().getFormat("0.0%"));
				}
				
				cell.setCellStyle(style);
				
				
			}
			
			c++;
		}
	}
	
	private OntoUMLModelStatistic createDiagnostic(String pathName){
		try {
			System.out.println(TimeHelper.getTime()+" - "+pathName+": loading parser...");
			OntoUMLParser parser = new OntoUMLParser(pathName);
			OntoUMLModelStatistic diagnostic = new OntoUMLModelStatistic(parser);
			return diagnostic;
		} catch (IOException e) {
			System.out.println(TimeHelper.getTime()+" - "+pathName+": unable to load parser");
			return null;
		}
	}
	
	private void createSheets(){
		for (LineType line : LineType.values()) {
			workbook.createSheet(line+" (Count)");
			workbook.createSheet(line+" (Type %)");
			workbook.createSheet(line+" (%)");
		}
	}
	
	private ArrayList<String> loadFileNamesInDirectory(){
		File dir = new File(DIR);
		for (File file : dir.listFiles()) {
			if (file.getName().endsWith((REFONTO)))
				textFiles.add(file.getName());
		}
		
		return textFiles;
	}
	
	public void createFile(){
		try{
			String fileName = DIR+OUTPUT_FILE;
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(fileName));
            workbook.write(out);
            out.close();
            System.out.println(TimeHelper.getTime()+" - "+fileName+": written successfully on disk.");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
	}

}

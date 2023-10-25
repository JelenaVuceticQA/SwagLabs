package Base;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
//Deklarisemo promenljive
    File file;
    FileInputStream fis; //citanje podataka iz excel datoteke
    XSSFWorkbook wb; //  objekat koji predstavlja Excel radnu knjigu
    XSSFSheet sheet; //objekat koji predstavlja radni list u Excel-u
    XSSFRow row; //objekat koji predstavlja red u radnom listu
    XSSFCell cell; //objekat koji predstavlja ćeliju u redu



    //Konstruktor prima putanju do Excel datoteke
    public ExcelReader(String filePath) throws IOException {
        file = new File(filePath);
        fis = new FileInputStream(file);
        wb = new XSSFWorkbook(fis);

    }
//Uzima radni list, red i ćeliju, a zatim vraća vrednost ćelije kao string pomoću  posl.koda
    public String getStringData(String sheetName, int rowNumber, int cellNumber) {
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rowNumber);
        cell = row.getCell(cellNumber);
        return cell.getStringCellValue();
    }
//Uzima radni list i vraća broj poslednjeg reda u radnom listu pomoću posl.koda
    public int getLastRow(String sheet) {
        this.sheet=wb.getSheet(sheet);
        return this.sheet.getLastRowNum();
    }


    }



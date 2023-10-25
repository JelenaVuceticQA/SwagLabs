package Base;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebTestWithExcel {
    private String filePath;

    public WebTestWithExcel(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, String> getUserData(String sheetName, int rowNum) {
        Map<String, String> userData = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);

            // Čuvanje korisničkog imena i lozinke iz Excel tabele
            Cell cellUsername = row.getCell(0);
            String username = (cellUsername != null) ? cellUsername.getStringCellValue() : "";

            Cell cellPassword = row.getCell(1);
            String password = (cellPassword != null) ? cellPassword.getStringCellValue() : "";

            // Korisničko ime i lozinka se dodaju u HashMap pod ključevima "username" i "password".
            userData.put("username", username);
            userData.put("password", password);

        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }

        return userData;
    }

    public static void main(String[] args) {
        WebTestWithExcel webTest = new WebTestWithExcel("C:\\Users\\Seka\\IdeaProjects\\SwagLabs\\src\\test\\java\\Base\\datotekaProjekat.xlsx");


        //petljom prolazi kroz redove radnog lista "Sheet1" i prikazuju se korisnički podaci
        for (int i = 1; i <= 4; i++) {
            Map<String, String> userData = webTest.getUserData("Sheet1", i);

            String username = userData.get("username");
            String password = userData.get("password");

            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
        }
    }
}
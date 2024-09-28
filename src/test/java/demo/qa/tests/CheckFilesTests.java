package demo.qa.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckFilesTests {
    private ClassLoader cl = CheckFilesTests.class.getClassLoader();
    boolean fileIsFound;

    @Test
    @DisplayName("Проверка содержимого PDF файла")
    void checkPDFFileContent() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(Objects
                .requireNonNull(cl.getResourceAsStream("files.zip")))) {
            ZipEntry zipEntry;
            fileIsFound = false;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().contains(".pdf")) {
                    fileIsFound = true;
                    PDF pdf = new PDF(zis);
                    assertThat(pdf.text).contains("Lorem Ipsum");
                }
            }
            if (!fileIsFound) {
                throw new Exception("There is no any PDF file in the archive");
            }
        }

    }

    @Test
    @DisplayName("Проверка содержимого XLSX файла")
    void checkXLSXFileContent() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(Objects
                .requireNonNull(cl.getResourceAsStream("files.zip")))) {
            ZipEntry zipEntry;
            fileIsFound = false;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().contains(".xlsx")) {
                    fileIsFound = true;
                    XLS xlsx = new XLS(zis);
                    assertThat(xlsx.excel.getSheetAt(0)).isNotEmpty();
                }
            }
            if (!fileIsFound) {
                throw new Exception("There is no any XLSX file in the archive");
            }
        }
    }


    @Test
    @DisplayName("Проверка содержимого CSV файла")
    void checkCSVFileContent() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(Objects
                .requireNonNull(cl.getResourceAsStream("files.zip")))) {
            ZipEntry zipEntry;
            fileIsFound = false;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().contains(".csv")) {
                    fileIsFound = true;
                    CSVReader csv = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csv.readAll();
                    assertThat(data.size()).isEqualTo(3);
                    assertThat(data.get(0)).isEqualTo(new String[] {"Cat", "Jack"});
                    assertThat(data.get(1)).isEqualTo(new String[] {"Dog", "Rex"});
                    assertThat(data.get(2)).isEqualTo(new String[] {"Rabbit", "MQ"});
                }
            }
            if (!fileIsFound) {
                throw new Exception("There is no any CSV file in the archive");
            }
        }
    }
}
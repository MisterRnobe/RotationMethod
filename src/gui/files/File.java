package gui.files;

import core.Matrix;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Dell on 08.05.2017.
 */
public class File {
    public static Matrix loadMatrix(String s) throws IOException, NullPointerException
    {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(s));
        XSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
        XSSFRow row = myExcelSheet.getRow(0);

        LinkedList<Double> d = new LinkedList<>();
        int N;

        for (N = 0; row.getCell(N) != null; N++) {
            d.push(row.getCell(N).getNumericCellValue());
        }
        N--;
        for (int i = 1; i < N; i++) {
            row = myExcelSheet.getRow(i);
            for (int j = 0; j < N+1; j++) {
                    d.push(row.getCell(j).getNumericCellValue());
            }
        }

        Double[] array = new Double[N*(N+1)];
        for (int i = 0; d.size()!=0; i++) {
            array[i] = d.removeLast();
        }
        Matrix m = new Matrix(N, array);
        myExcelBook.close();
        return m;
    }
    public static void saveSolutions(double[] solutions, String file) throws IOException
    {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Solutions");

        // Нумерация начинается с нуля

        Cell cell;
        for (int i = 0; i < solutions.length; i++) {
            Row row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(solutions[i]);
        }

        sheet.autoSizeColumn(1);

        // Записываем всё в файл
        book.write(new FileOutputStream(file));
        book.close();
    }
}

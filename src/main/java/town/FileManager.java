package town;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.ArrayDeque;


public class FileManager {
    static String saveLocation = "save";
    static {
        String tmp = System.getenv("PROG_5_CSV");
        if (tmp!=null) {saveLocation = tmp;}
        else {System.out.println("Не получилось использовать переменную окружения!!!!!! >:(");}
    }
    public static void writeCSV(ArrayDeque<City> cities) {
        Writer writer = null;
        try {
            writer = new FileWriter(saveLocation);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            for (City c : cities){
                beanToCsv.write(c);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        } catch (CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        }
    }
    public static void readCSV(){
        City.isLoading = true;
    BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(saveLocation));
            ArrayDeque<City> beans = new ArrayDeque<>();
            beans.addAll(new CsvToBeanBuilder(br)
                    .withType(City.class).build().parse());
            CollectionManager.setCity(beans);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            City.isLoading = false;
        }
    }

}
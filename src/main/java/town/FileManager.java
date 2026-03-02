package town;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.*;
import java.util.ArrayDeque;

/**
 * Класс для управления сохранением и загрузкой коллекции городов в/из CSV файл
 * Путь к файлу сохранения определяется через переменную окружения {@code PROG_5_CSV},
 * по умолчанию используется файл {@code "save"}.
 * </p>
 * @see City
 * @see CollectionManager
 * @see com.opencsv.bean.StatefulBeanToCsv
 * @see com.opencsv.bean.CsvToBeanBuilder
 */
public class FileManager {
    static String saveLocation = "save";
    static {
        String tmp = System.getenv("PROG_5_CSV");
        if (tmp!=null) {saveLocation = tmp;}
        else {System.out.println("Не получилось использовать переменную окружения!!!!!! >:(");}
    }
    /**
     * Записывает коллекцию городов в CSV файл
     * Каждый город записывается как отдельная строка в CSV формате
     * </p>
     * @param cities коллекция городов для сохранения
     * @throws RuntimeException если возникает ошибка ввода-вывода или несоответствие типов данных
     * @see StatefulBeanToCsv
     * @see StatefulBeanToCsvBuilder
     * @see CsvDataTypeMismatchException
     * @see CsvRequiredFieldEmptyException
     */
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
    /**
     * Загружает коллекцию городов из CSV файла
     * Метод устанавливает флаг {@link City#isLoading} в {@code true} для отключения
     * интерактивного ввода при создании объектов
     * После загрузки флаг сбрасывается в {@code false}.
     * Загруженная коллекция устанавливается в {@link CollectionManager} через метод {@link CollectionManager#setCity(ArrayDeque)}.
     * </p>
     * @throws RuntimeException если файл не найден или возникает ошибка парсинга CSV
     * @see CsvToBeanBuilder
     * @see City#isLoading
     * @see CollectionManager#setCity(ArrayDeque)
     * @see java.io.BufferedReader
     * @see java.io.FileReader
     */
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
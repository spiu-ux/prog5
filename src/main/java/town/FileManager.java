package town;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
     * Записывает коллекцию городов в CSV файл с использованием построчной записи через {@link FileOutputStream}.
     * Применяет цепочку потоков {@code FileOutputStream → OutputStreamWriter (UTF-8) → BufferedWriter}
     * @param cities коллекция городов для сохранения в файл
     * @throws RuntimeException при возникновении ошибок ввода-вывода или несоответствия типов данных
     */
    public static void writeCSV(ArrayDeque<City> cities) {
        try (
                FileOutputStream fos = new FileOutputStream(saveLocation);
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                BufferedWriter bw = new BufferedWriter(osw)
        ) {
            StatefulBeanToCsv<City> beanToCsv = new StatefulBeanToCsvBuilder<City>(bw)
                    .withApplyQuotesToAll(false)
                    .build();
            for (City city : cities) {
                beanToCsv.write(city);
            }
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        } catch (CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Загружает коллекцию городов из CSV-файла
     * Метод использует {@link FileReader} для чтения данных
     * Перед загрузкой устанавливает флаг {@link City#isLoading} в {@code true}, чтобы отключить
     * интерактивный ввод в конструкторах классов
     * После завершения загрузки флаг сбрасывается в {@code false}.
     * Если файл не найден, создаётся пустая коллекция без генерации исключения.
     * @throws RuntimeException при ошибках ввода-вывода (кроме отсутствия файла)
     * @see FileReader
     * @see CsvToBeanBuilder
     * @see City#isLoading
     * @see CollectionManager#setCity(ArrayDeque)
     */
    public static void readCSV() {
        City.isLoading = true;
        Integer maxCount=0;
        List<City> validCities = new ArrayList<>();
        HashSet<Integer> idSet = new HashSet<>();
        int skippedCount = 0;
        try (FileReader fr = new FileReader(saveLocation);
             BufferedReader br = new BufferedReader(fr)) {
            List<City> parsedCities = new CsvToBeanBuilder<City>(br)
                    .withType(City.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
            for (City city : parsedCities) {
                if (city != null && city.validate()) {
                    if(idSet.contains(city.getId())){
                        continue;
                    }
                    idSet.add(city.getId());
                    validCities.add(city);
                    if(maxCount < city.getId()){
                        maxCount = city.getId();
                    }
                } else {
                    skippedCount++;
                    System.err.println("Пропущена некорректная запись: " +
                            (city != null ? city.getName() : "null") +
                            " в файле " + saveLocation);
                }
            }
            if (!idSet.isEmpty()){
                City.setCount(maxCount+1);
            }
            CollectionManager.setCity(new ArrayDeque<>(validCities));
            System.out.println("Загружено " + validCities.size() + " городов из файла: " + saveLocation);
            if (skippedCount > 0) {
                System.out.println("Пропущено " + skippedCount + " некорректных записей.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + saveLocation + ". Создана пустая коллекция.");
            CollectionManager.setCity(new ArrayDeque<>());
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла " + saveLocation + ": " + e.getMessage());
            throw new RuntimeException("Не удалось загрузить данные из файла", e);
        } finally {
            City.isLoading = false;
        }
    }
}
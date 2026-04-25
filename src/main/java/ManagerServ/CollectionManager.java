package ManagerServ;

import town.City;
import town.Human;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Класс, ответственный за все изменения в коллекции
 */
public class CollectionManager {
    /** Коллекция городов. */
    static ArrayDeque<City> cities = new ArrayDeque<>();
    /** Время инициализации коллекции. */
    static java.time.LocalDateTime initTime = java.time.LocalDateTime.now();
    /**
     * Очищает коллекцию.
     */
    public static void clear(){
        cities.clear();
    }
    /**
     * Добавляет новый город в коллекцию.
     */
    public static void add(){
        cities.add(new City());
    }
    /**
     * Выводит информацию о коллекции.
     */
    public static void info(){
        System.out.printf("Тип коллекции: %s\n"+
                "Количество элементов: %s\n"+
                "Время создания: %s\n", cities.getClass(),cities.size(),initTime);
    }
    /**
     * Выводит все города в коллекции.
     */
    public static void show(){
        for (City c : cities){
            System.out.println(c);
        }
    }
    /**
     * Удаляет первый город из коллекции.
     */
    public static void removeFirst(){
        cities.removeFirst();
    }
    /**
     * Удаляет город по идентификатору
     * @param id идентификатор города
     */
    public static void removeById(Integer id){
        for(City c: cities){
            if(c.getId().equals(id)){
                cities.remove(c);
                System.out.println("Город успешно удален!!!!!.");
                return;
            }
        }
        System.out.println("Город не удален!!!!!.");
    }
    /**
     * Выводит уникальных правителей городов из коллекции.
     * Метод использует {@code HashMap} для фильтрации дубликатов на основе хеш-кода правителя.
     * Уникальность определяется по комбинации полей {@code height} и {@code birthday}.
     * </p>
     * @see Human#hashCode()
     * @see Human#equals(Object)
     */
    public static void printUniqueGovernor(){
        HashMap<Integer, Human> uniqueGovernors= new HashMap<>();
        for(City c: cities){
            Human governor = c.getGovernor();
            uniqueGovernors.put(governor.hashCode(),governor);
        }
        System.out.println(uniqueGovernors.values());
        for (Human h: uniqueGovernors.values())
            System.out.println(h);
        }
    /**
     * Выводит города, название которых содержит заданную подстроку.
     * @param pattern подстрока для поиска в названиях городов
     */
    public static void filterContainsName(String pattern){
        for(City c: cities){
            if(c.getName().contains(pattern)){
                System.out.println(c);
            }
        }
    }
    /**
     * Обновляет поля города по идентификатору через ввод от пользователя
     * @param id идентификатор города для обновления
     */
    public static void updateById(Integer id) {
        City c = findById(id);
        if (c == null) {return;}
        c.update();
    }
    /**
     * Находит город в коллекции по идентификатору
     * @param id идентификатор города
     * @return найденный город или {@code null}, если город не найден
     */
    private static City findById(Integer id) {
        for (City c: cities) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        System.out.printf("Город с идентификатором '%s' не найден.\n", id);
        return null;
    }
    /**
     * Добавляет город в коллекцию, если его население больше всех существующих городов
     * @param newCity город для добавления
     */
    public static void addIfMax(City newCity) {
        for (City c: cities) {
            if (newCity.compareTo(c) < 0) {
                System.out.println("Город не был добавлен, он не является максимальным:(");
                return;
            }
        }
        cities.add(newCity);
        System.out.println("Город добавлен!");
    }
    /**
     * Удаляет все города из коллекции, у которых население меньше, чем у заданного города
     * @param newCity город для сравнения
     */
    public static void removeIfLess(City newCity) {
        //HashSet<City> toRemove = new HashSet<>(); не нужен, ничего не ломается)
        for (City c : cities) {
            if (newCity.compareTo(c) > 0) {
                cities.remove(c);
                System.out.printf("Город '%s' удален.\n", c);
            }
        }
    }
    /**
     * Выводит города в порядке: сначала столицы, затем остальные города
     */
    public static void sortByCapitalDescending() {
        cities.stream()
                .sorted(Comparator.comparing(City::getCapital).reversed())
                .forEach(System.out::println);
    }
    /**
     * Возвращает коллекцию городов
     * @return коллекция городов
     */
    public static ArrayDeque<City> getCity(){
        return cities;
    }
    /**
     * Устанавливает коллекцию городов.
     * @param c новая коллекция
     */
    public static void setCity(ArrayDeque<City> c){
        cities = c;
    }
}

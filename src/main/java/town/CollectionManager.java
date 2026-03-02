package town;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
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
        HashMap<Integer, Human> dino = new HashMap<>();
        for(City c: cities){
            Human human_t = c.getGovernor();
            dino.put(human_t.hashCode(),human_t);
        }
        System.out.println(dino.values());
        for (Human h: dino.values())
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
    public static void update_id(Integer id) {
        City c = find_by_id(id);
        if (c == null) {return;}
        c.update();
    }
    /**
     * Находит город в коллекции по идентификатору
     * @param id идентификатор города
     * @return найденный город или {@code null}, если город не найден
     */
    private static City find_by_id(Integer id) {
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
    public static void add_if_max(City newCity) {
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
    public static void remove_el(City newCity) {
        HashSet<City> toRemove = new HashSet<>();
        for (City c: cities) {
            if (newCity.compareTo(c) > 0) {
                toRemove.add(c);
            }
        }
        for (City c: toRemove) {
            cities.remove(c);
            System.out.printf("Город '%s' удален.\n", c);
        }
        toRemove.clear();
    }
    /**
     * Выводит города в порядке: сначала столицы, затем остальные города
     */
    public static void desc_capital(){
        ArrayDeque<City> cities_i = cities.clone();
        for(City c: cities.clone()){
            if(c.getCapital()){
                System.out.println(c.getCapital());
                cities_i.remove(c);
            }
        }
        for(City c: cities_i) {
                System.out.println(c.getCapital());
        }
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

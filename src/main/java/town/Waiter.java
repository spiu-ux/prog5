package town;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
/**
 * Класс для интерактивного ввода данных от пользователя
 */
public class Waiter {
    //enumы, Long, Double, String, boolean
    /** Сканер для чтения ввода пользователя. */
    public static Scanner sc = new Scanner(System.in);
    /**
     * Запрашивает у пользователя выбор значения из перечисления.
     * @param name название поля для вывода пользователю
     * @param enumClass класс перечисления
     * @param constants массив допустимых значений перечисления
     * @param <E> тип перечисления
     * @return выбранное значение перечисления
     */
    public static <E extends Enum<E>> E enumChoice(String name, Class<E> enumClass, E[] constants) {
        System.out.println("\nВыберите " + name + ":");
        System.out.print("Доступные значения: ");
        for (int i = 0; i < constants.length; i++) {
            System.out.print(constants[i]);
            if (i < constants.length - 1) System.out.print(", ");
        }
        System.out.println("\n");
        while (true) {
            System.out.print(">> ");
            String input = sc.nextLine().trim().toUpperCase();
            if (input.isBlank()) {
                System.out.println("Ввод не может быть пустым");
                continue;
            }
            try {
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e) {
                System.out.println(input + "' не является допустимым значением.");
                System.out.print("Доступные значения: ");
                for (int i = 0; i < constants.length; i++) {
                    System.out.print(constants[i]);
                    if (i < constants.length - 1) System.out.print(", ");
                }
                System.out.println("\nПопробуйте снова.");
            }
        }
    }
    /**
     * Запрашивает у пользователя целое число больше 0.
     * @param name название поля для вывода пользователю
     * @return введённое целое число
     */
    public static Integer getInteger(String name){
        Integer input;
        System.out.println("Введите число > 0 "+ name + ":" );
        while (true) {
            System.out.print(">> ");
            try{
                input = Integer.parseInt(sc.nextLine());
                if (input>0){
                    return input;
                }
                System.out.println("Введите число > 0:");
            }catch (NumberFormatException e){
                System.out.println("Введите число!!!!!!");
            }
        }
    }
    /**
     * Запрашивает у пользователя дату в формате ГГГГ-ММ-ДД.
     * @return введённая дата
     */
    public static java.time.LocalDate getDate(){
       System.out.println("Введите дату (ГГГГ-ММ-ДД): ");
       while (true){
           System.out.print(">> ");
       try {
           java.time.LocalDate input = java.time.LocalDate.parse(sc.nextLine());
           return input;
       } catch (DateTimeParseException e) {
           System.out.println("Введите дату (ГГГГ-ММ-ДД)!!!!!!");
            }
       }
    }
    /**
     * Запрашивает у пользователя число типа {@code float}.
     * @param name название поля для вывода пользователю
     * @param isPositive флаг, требующий при значении числа < 0 {@code false} или при значении числа > 0 {@code true}.
     * @return введённое число
     */
    public static float getFloat(String name,boolean isPositive){
        float input;
        System.out.println("Введите число "+ name + ":" );
        while (true) {
            System.out.print(">> ");
            try{
                input = Float.parseFloat(sc.nextLine());
                if(isPositive){
                    if(input<=0){
                        System.out.println("Введите число > 0!!!!!!!!!!");
                        continue;
                    }
                }
                    return input;
            }catch (NumberFormatException e){
                System.out.println("Введите число!!!!!!");
            }
        }
    }
    /**
     * Запрашивает у пользователя число типа {@code double}.
     * @param name название поля для вывода пользователю
     * @return введённое число
     */
    public static double getDouble(String name){
        double input;
        System.out.println("Введите число "+ name + ":" );
        while (true) {
            System.out.print(">> ");
            try{
                input = Double.parseDouble(sc.nextLine());
                return input;
            }catch (NumberFormatException e){
                System.out.println("Введите число!!!!!!");
            }
        }
    }
    /**
     * Запрашивает у пользователя непустую строку.
     * @param name название поля для вывода пользователю
     * @return введённая строка
     */
    public static String getString(String name){
        String input;
        System.out.println("Введите "+ name + ":" );
        while (true) {
            System.out.print(">> ");
            input = sc.nextLine();
            if(!input.isBlank()){
                return input;
            }
            System.out.println("Строка не может быть пустой!!!!!");
        }
    }
    /**
     * Запрашивает у пользователя целое число типа {@code Long} > 0.
     * @param name название поля для вывода пользователю
     * @return введённое число
     */
    public static Long getLong(String name){
        Long input;
        System.out.println("Введите число > 0 "+ name + ":" );
        while (true) {
            System.out.print(">> ");
            try{
                input = Long.parseLong(sc.nextLine());
                if (input>0){
                    return input;
                }
                System.out.println("Введите число > 0:");
            }catch (NumberFormatException e){
                System.out.println("Введите число!!!!!!");
            }
        }
    }
    /**
     * Запрашивает у пользователя {@code boolean} значение,{@code false} по умолчанию.
     * @param name название поля для вывода пользователю
     * @return введённое значение
     */
    public static Boolean getBoolean(String name){
        System.out.println("Является ли "+ name + " столицей? (true/False)" );
        while(true){
            System.out.print(">> ");
            Boolean input = Boolean.parseBoolean(sc.nextLine());
            return input;
        }
    }
}

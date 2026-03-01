package town;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Waiter {
    //enumы, Long, Double, String, boolean
    public static Scanner sc = new Scanner(System.in);

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
    public static Boolean getBoolean(String name){
        System.out.println("Является ли "+ name + " столицей? (true/False)" );
        while(true){
            System.out.print(">> ");
            Boolean input = Boolean.parseBoolean(sc.nextLine());
            return input;
        }
    }
}

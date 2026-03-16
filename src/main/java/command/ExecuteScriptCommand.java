package command;
import town.Waiter;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Команда для выполнения скрипта из файла
 * Считывает команды из указанного файла и выполняет их последовательно
 * @see Command
 * @see Invoker
 * @see Waiter
 */
public class ExecuteScriptCommand implements Command{
    HashSet<String> openScripts = new HashSet<>();
    public String desc(){
        return "Считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
    /**
     *@param args массив аргументов команды, где {@code args[0]} — имя файла скрипта
     * @throws ArrayIndexOutOfBoundsException если не указано имя файла скрипта
     * @see FileReader
     * @see Scanner
     * @see Invoker#executeCommand(String, String[])
     */
    public void execute(String[] args){
        StringBuilder text = new StringBuilder();
        try (FileReader fr = new FileReader("scripts/" + args[0])) {
            if(openScripts.contains(args[0])){
                System.out.println("Вызывается рекурсия:(");
                return;
            }
            int i = fr.read();
            while(i != -1) {
                text.append((char)i);
                i = fr.read();
            }
            openScripts.add(args[0]);
        } catch (IOException e) {
            System.out.println("Проблемы с файлом!!!!");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Введите файл");
        }
        //System.out.println(text);
        Scanner sc = new Scanner(text.toString());
        Waiter.sc = sc;
        while(sc.hasNext()) {
            try {
                String[] data = sc.nextLine().split(" ");
                String commandName = data[0];
                Invoker.executeCommand(commandName, Arrays.copyOfRange(data, 1, data.length));
            } catch (NullPointerException e) {
                System.out.println("Такой команды не существует:(");
            }
        }
        Waiter.sc = new Scanner(System.in);
        try{
            openScripts.remove(args[0]);
        } catch (ArrayIndexOutOfBoundsException ignored) { }
    }
}

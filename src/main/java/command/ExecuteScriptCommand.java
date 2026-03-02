package command;
import town.Waiter;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Команда для выполнения скрипта из файла
 * Считывает команды из указанного файла и выполняет их последовательно
 * @see Command
 * @see Invoker
 * @see Waiter
 */
public class ExecuteScriptCommand implements Command{
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
            int i = fr.read();
            while(i != -1) {
                text.append((char)i);
                i = fr.read();
            }
        } catch (IOException e) {
            System.out.println("Проблемы с файлом!!!!");
        }
        //System.out.println(text);
        Waiter.sc = new Scanner(text.toString());
        while(Waiter.sc.hasNext()) {
            try {
                String[] data = Waiter.sc.nextLine().split(" ");
                String commandName = data[0];
                Invoker.executeCommand(commandName, Arrays.copyOfRange(data, 1, data.length));
            } catch (NullPointerException e) {
                System.out.println("Такой команды не существует:(");
            }
        }
        Waiter.sc = new Scanner(System.in);
    }
}

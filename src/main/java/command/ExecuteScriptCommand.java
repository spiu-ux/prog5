package command;
import town.Waiter;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class ExecuteScriptCommand implements Command{
    public String desc(){
        return "Считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
    public void execute(String[] args){
        StringBuilder text = new StringBuilder();
        try (FileReader fr = new FileReader(args[0])) {
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

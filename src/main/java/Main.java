import command.Invoker;

import town.CollectionManager;
import town.FileManager;
import town.Waiter;

import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        Scanner sc = Waiter.sc;
        FileManager.readCSV();
        while (true) {
            System.out.print(">> ");
            String[] data = sc.nextLine().split(" ");
            String  commandName = data[0];
            try{
                Invoker.executeCommand(commandName, Arrays.copyOfRange(data,1, data.length));
            } catch (NullPointerException e){
                System.out.println("Такой команды нет, попробуйте 'help'");
            }catch (java.util.NoSuchElementException e){
                System.out.println("Работа завершена, так как вы закрыли поток:) Перезапустите!");
                break;
            }
        }
    }
}

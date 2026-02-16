import command.Invoker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(">> ");
            String[] data = sc.nextLine().split(" ");
            String  commandName = data[0];
            try{
                Invoker.executeCommand(commandName);
            } catch (NullPointerException e){
                System.out.println("такой команды нет, попробуйте 'help'");
            }
        }
    }
}

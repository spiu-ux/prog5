package command;
import java.util.HashMap;

public class Invoker {
    static HashMap<String, Command> command =new HashMap<>();


    static{
        command.put("exit", new ExitCommand());
        command.put("help", new HelpCommand());

    }

    public static void executeCommand(String name){
        command.get(name).execute();
    }
}

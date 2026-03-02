package command;
import java.util.HashMap;

/**
 * Класс, отвечающий за выполнение команд
 */
public class Invoker {
    static HashMap<String, Command> command =new HashMap<>();


    static{
        command.put("exit", new ExitCommand());
        command.put("help", new HelpCommand());
        command.put("add", new AddCommand());

        command.put("info", new InfoCommand());
        command.put("show", new ShowCommand());
        command.put("update", new UpdateCommand());
        command.put("removeFirst", new RemoveFirstCommand());
        command.put("removeById",new RemoveByIdCommand());
        command.put("printUniqueGovernor", new PrintUniqueGovernorCommand());
        command.put("filterContainsName", new FilterContainsNameCommand());
        command.put("addIfMax", new AddIfMaxCommand());
        command.put("removeLower", new RemoveLowerCommand());
        command.put("printFieldDescendingCapital", new PrintFieldDescendingCapitalCommand());
        command.put("clear", new ClearCommand());

        command.put("save", new SaveCommand());
        command.put("load",new LoadCommand());

        command.put("executeScript", new ExecuteScriptCommand());

    }
    /**
     * Выполнить указанную команду
     * @param name String name of the command
     * @param args command arguments
     */
    public static void executeCommand(String name,String[] args){
        command.get(name).execute(args);
    }
}

package command;

public class HelpCommand implements Command{
    public void execute(String[] args){
        for (String key: Invoker.command.keySet()){
            System.out.printf("%s - %s\n", key, Invoker.command.get(key).desc());
        }
    }
    public String desc(){
        return "вывести справку по доступным командам";
    }
}

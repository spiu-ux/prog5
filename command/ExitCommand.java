package command;

public class ExitCommand implements Command{
    public String desc(){
        return "выйти из команды без сохранения";
    }
    public void execute(){
        System.exit(5);
    }
}

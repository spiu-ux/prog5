package command;

public class ExitCommand implements Command{
    public String desc(){
        return "выйти из команды без сохранения";
    }
    public void execute(String[] args){
        System.exit(5);
    }
}

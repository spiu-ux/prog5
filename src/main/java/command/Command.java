package command;

public interface Command {
    public void execute(String[] args);
    public String desc();
}

package command;
/**
 * Command interface
 */
public interface Command {
    /**
     * Выполнить команду
     * @param args аргументы команды
     */
    public void execute(String[] args);
    /**
     * Получить описание команды
     * @return описание
     */
    public String desc();
}

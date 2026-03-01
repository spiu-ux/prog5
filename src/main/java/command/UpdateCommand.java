package command;

import town.CollectionManager;

public class UpdateCommand implements Command{
    public String desc() {
        return "обновите элемент с помощью {id}";
    }
    public void execute(String[] args) {
        Integer id;
        try {
            id = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Нет такого id!");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Укажите id города!!!!!");
            return;
        }
        CollectionManager.update_id(id);
    }
}

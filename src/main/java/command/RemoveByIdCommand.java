package command;

import town.CollectionManager;

public class RemoveByIdCommand implements Command{
    public String desc(){
        return "Удаляет первый объект по указанному id из коллекции";
    }
    public void execute(String[] args){
        try{
            Integer id = Integer.parseInt(args[0]);
            CollectionManager.removeById(id);
        } catch (NumberFormatException e) {
            System.out.println("Введен некорректный id");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Вы не ввели id");
        }
    }
}

package command;

import town.CollectionManager;

public class ShowCommand implements Command{
    public String desc() {
        return "шоу зе контентс оф взэ линкд лист";
    }

    public void execute(String[] args) {
            CollectionManager.show();
        }
    }


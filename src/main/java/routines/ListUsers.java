package routines;

import classes.DAO.UserDAO;
import classes.common.User;
import classes.common.printerDetail;

import java.util.List;
import java.util.Scanner;

import static routines.Utility.confirmPrompt;
import static routines.Utility.printer;

public abstract class ListUsers {

    static UserDAO usrDAO;

    private static UserDAO getUserDAO(){
        if(usrDAO == null){
            usrDAO = new UserDAO();
        }
        return usrDAO;
    }

    private static void renderHead(Scanner input){

    }
    public static void render(Scanner input){
        printer();
        printer("Listagem de Usuários");
        printer();
        printer(getUserDAO().getUsersList());
        printer();
        printer("Precione ENTER para continuar . . .");
        printer();
        input.nextLine();
    }

    public static void renderByName(Scanner input){
        while(true){
            printer();
            printer("Listagem de Usuários");
            printer("Digite o Nome do Usuário");
            printer();
            String str = input.nextLine();
            List<User> users = getUserDAO().getUsersByName(str);
            if(users.size() > 0){
                printer(users);
            } else {
                printer();
                printer("Usuário "+str+" encontrado!");
                printer();
            }
            if(!confirmPrompt(input,"Gostaria de fazer uma nova busca?")) break;
        }



    }


    public static void renderArg(Scanner input, String arg){
        switch (arg){
            case "all" -> render(input);
            case "byName" -> renderByName(input);
        }
    }
}

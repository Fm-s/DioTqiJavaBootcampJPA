package routines;

import classes.DAO.AccountDAO;
import classes.DAO.UserDAO;
import classes.common.User;

import java.util.List;
import java.util.Scanner;

import static routines.Utility.confirmPrompt;
import static routines.Utility.printer;

public abstract class ListAccounts {
    private static AccountDAO accDAO;
    private static UserDAO usrDAO;

    private static UserDAO getUserDAO() {
        if (usrDAO == null) {
            usrDAO = new UserDAO();
        }
        return usrDAO;
    }
    private static AccountDAO getAccountDAO(){
        if(accDAO == null){
            accDAO = new AccountDAO();
        }
        return accDAO;
    }

    public static void renderByName(Scanner input) {
        while (true) {
            printer();
            printer("Listagem de Contas");
            printer("Digite o Nome do Usuário viculado a conta");
            printer();
            String str = input.nextLine();
            List<User> users = getUserDAO().getUsersByName(str);
            if (users.size() > 0) {
                for (User usr : users) {
                    if (usr.getAccounts().size() == 0) {
                        printer();
                        printer(usr.toString());
                        printer("não possui conta");
                        printer();
                    } else printer(usr.getAccounts());
                }
            } else {
                printer();
                printer("Usuário " + str + " encontrado!");
                printer();
            }
            if (!confirmPrompt(input, "Gostaria de fazer uma nova busca?")) break;
        }

    }

    public static void renderArg(Scanner input, String arg) {
        switch (arg) {
            case "all" -> render(input);
            case "byName" -> renderByName(input);
        }
    }

    public static void render(Scanner input) {
        printer();
        printer("Listagem de Contas");
        printer();
        printer(getAccountDAO().getAccountsList());
        printer();
        printer("Precione ENTER para continuar . . .");
        printer();
        input.nextLine();
    }
}

package routines;

import classes.common.User;
import classes.common.DbAcess;

import static routines.Utility.confirmPrompt;

import java.util.Scanner;

import static routines.Utility.printer;

abstract public class NewUser {
    private static void saveUser(String name) {
        DbAcess db = new DbAcess();
        db.add(new User(name));
        db.close();
    }

    public static void render(Scanner input) {

        while (true) {
            printer();
            printer("Novo Usuário");
            printer();
            printer("Digite o nome do usuário ou 0 para voltar");
            printer();
            String name = input.nextLine();

            if (name.equals("0")) break;

            if (confirmPrompt(input, "Nome do usuário: " + name)) {
                NewUser.saveUser(name);
            }
        }
    }
}

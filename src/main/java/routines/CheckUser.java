package routines;

import classes.common.User;

import java.util.Scanner;

import static classes.DAO.UserDAO.getUserById;
import static routines.Utility.*;

public abstract class CheckUser {
    public static void render(Scanner input) {
        while (true) {
            printer();
            printer("Menu de consulta de usuários");
            printer("opt");
            printer("1 - Consultar cliente por Codigo");
            printer("2 - Consultar cliente por Nome");
            printer("3 - Listar clientes");
            printer("0 - Voltar");
            printer();

            int opt = safeIntInput(input);

            if (opt == 0) break;

            switch (opt) {
                case 1 -> {
                    while (true) {
                        printer();
                        printer("Consulta por CODIGO");
                        printer();
                        printer("Digite o CODIGO do cliente ou 0 pra voltar:");
                        printer();
                        int codigo = safeIntInput(input);
                        if (codigo == 0) break;
                        if (codigo > 0) {
                            User user = getUserById(codigo);
                            if (user != null) {
                                printer();
                                printer("Usuário Encontrato:");
                                printer(user.toString());
                                printer();
                            } else {
                                printer();
                                printer("Codigo não encontrado!");
                                printer("Tente novamente . . .");
                                printer();
                            }

                            if (!confirmPrompt(input, "Gostaria de Consultar outro Codigo?")) break;
                        } else {
                            printer("invalid");
                        }
                    }
                }

                case 2, 3 -> {
                    String option;
                    if (opt == 2) option = "byName";
                    else option = "all";
                    ListUsers.renderArg(input, option);
                }
            }
        }
    }
}

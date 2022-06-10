package routines;

import classes.accounts.Account;
import classes.common.User;

import java.util.Scanner;

import static classes.DAO.AccountDAO.getAccountById;
import static classes.DAO.UserDAO.getUserById;
import static routines.Utility.*;
import static routines.Utility.printer;

public abstract class CheckAccount {
    public static void render(Scanner input) {
        while (true) {
            printer();
            printer("Menu de consulta de Contas");
            printer("opt");
            printer("1 - Consultar por Codigo");
            printer("2 - Consultar por Codigo do Cliente");
            printer("3 - Consultar por Nome do Cliente");
            printer("4 - Listar Contas");
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
                        printer("Digite o CODIGO da Conta ou 0 pra voltar:");
                        printer();
                        int codigo = safeIntInput(input);
                        if (codigo == 0) break;
                        if (codigo > 0) {
                            Account acc = getAccountById(codigo);
                            if (acc != null) {
                                printer();
                                printer("Conta Encontrada:");
                                printer(getAccountById(codigo).toStringDetail());
                                printer();
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
                case 2 -> {
                    while (true) {
                        printer();
                        printer("Consulta por CODIGO do Cliente");
                        printer();
                        printer("Digite o CODIGO do Cliente ou 0 pra voltar:");
                        printer();
                        int codigo = safeIntInput(input);
                        if (codigo == 0) break;
                        if (codigo > 0) {
                            User usr = getUserById(codigo);
                            printer();
                            if (usr != null) {
                                printer("Contas Encontradas:");
                                printer(usr.getAccounts());
                                printer();
                            } else {
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
                case 3 -> ListAccounts.renderArg(input, "byName");
                case 4 -> ListAccounts.render(input);
            }
        }
    }
}





package routines;

import classes.accounts.Account;

import java.util.Scanner;

import static classes.DAO.AccountDAO.getAccountById;
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
                        printer("Digite o CODIGO do Conta ou 0 pra voltar:");
                        printer();
                        int codigo = safeIntInput(input);
                        if (codigo == 0) break;
                        if (codigo > 0) {
                            Account acc = getAccountById(codigo);
                            if (acc != null) {
                                printer();
                                printer("Conta Encontrato:");
                                printer(acc.toString());
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
            }
        }
    }
}





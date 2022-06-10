package routines;

import classes.accounts.CheckingAccount;
import classes.accounts.SavingsAccount;
import classes.accounts.accountType;
import classes.common.DbAcess;
import classes.common.User;
import java.util.Scanner;

import static classes.DAO.UserDAO.getUserById;
import static routines.Utility.*;

public abstract class NewAccount {
    private static void saveAccount(User user, Double balance, int type) {
        try {
            DbAcess db = new DbAcess();
            if (type == 1) {
                db.add(new CheckingAccount(user, balance));
            } else {
                db.add(new SavingsAccount(user, balance));
            }
            printer();
            printer("Sucesso!");
            if (type == 1) printer("Conta Corrente Aberta!");
            if (type == 2) printer("Conta Poupança Aberta!");


            printer(user + " Balanço Inicial: " + formatDoubleToBRL(balance));
            printer();
        } catch (Exception e) {
            printer();
            printer("Erro ao Cadastrar! " + e.getMessage());
            printer("Usuário: " + user + " Balanço: " + balance + "Tido conta: " + type);
            printer();
        }
    }

    public static void render(Scanner input) {

        while (true) {
            printer();
            printer("Menu Abertura de Contas");
            printer("opt");
            printer("1 - Conta Corrente");
            printer("2 - Conta Poupança");
            printer("0 - Voltar ao menu anterior");
            printer();

            int option = safeIntInput(input);


            if (option >= 0) {
                if (option == 0) break;
                if (!(option == 1 || option == 2)) {
                    printer("invalid");
                    continue;
                }
            }

            while (true) {
                printer();
                printer("Digite o codigo do proprietário da nova conta ou 0 para voltar");
                printer();
                int id = safeIntInput(input);
                if (id == 0) break;
                User usr = getUserById(id);
                if (usr == null) {
                    printer("Usuário não encontrado!");
                    continue;
                }
                if (confirmPrompt(input, "Nome do usuário retornado: " + usr)) {
                    while (true) {
                        printer();
                        printer("Digite o saldo inicial da conta ou 0 para cancelar");
                        printer();
                        double balance = safeDoubleInputLocale(input, "pt_BR");
                        if (balance == 0) break;
                        if (balance > 0) {
                            if (confirmPrompt(input, "Valor do Saldo Inicial: " + formatDoubleToBRL(balance))) {
                                saveAccount(usr, balance, option);
                                break;
                            }
                        } else printer("Valor Invalido!");
                    }
                    break;
                }
            }
        }
    }
}
import classes.common.DbAcess;
import routines.*;

import java.util.Scanner;

import static routines.Utility.printer;
import static routines.Utility.safeIntInput;

public class TqiBootcamp {
    public static void main(String[] args) {


        printer("intro");
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                printer("Bem-vindo ao Inicio!");
                printer("opt");
                printer("1 - Cadastrar Cliente");
                printer("2 - Abrir Conta");
                printer("3 - Consultar Clientes");
                printer("4 - Status da Conta");
                printer("5 - Efetuar Depósito");
                printer("6 - Sacar Dinheiro");
                printer("7 - Transferência");
                printer("0 - Sair");
                printer();

                int i = safeIntInput(input);

                if (i == 0) break;
                switch (i) {
                    case 1 -> NewUser.render(input);
                    case 2 -> NewAccount.render(input);
                    case 3 -> CheckUser.render(input);
                    case 4 -> CheckAccount.render(input);
                    case 5,6,7 -> dummy.render(input);
                    default -> printer("invalid");
                }
            }
        }

    }
}


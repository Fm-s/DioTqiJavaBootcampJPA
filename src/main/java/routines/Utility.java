package routines;

import classes.common.printerDetail;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Utility {

    private static final String STD_STR = "|-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_|";

    public static int safeIntInput(Scanner input) {
        try {
            return Integer.parseInt(input.nextLine());
        } catch (Exception e) {
            printer();
            printer("Erro de Entrada: " + e.getMessage());
            printer("Digite Numeros Inteiros de 0 a 9");
            printer();
            return -1;
        }
    }

    public static double safeDoubleInputLocale(Scanner input, String locale) {
        String strNumber = input.nextLine();

        strNumber = strNumber.replace(".", "").replace(",", ".");

        try {
            return Double.parseDouble(strNumber);
        } catch (Exception e) {
            printer();
            printer("Numero formato inválido: " + e.getMessage());
            printer("Digite o valor na forma abaixo");
            printer("Ex: 10.000,00 ou 10000");
            printer();
        }
        return -1;
    }

    public static void printer() {
        System.out.println(STD_STR);
    }

    public static void printer(String str) {
        switch (str) {
            case "intro" -> {
                System.out.println("|-_-_-_-_-_-_-_-_-_-_-_+_-_-_-_-_-_-_-_-_-_+_-_-_-_-_-_-_-_-_-_-_-_|");
                System.out.println(fillString("| Bank Manager 2000 |", "-*", "|", "|"));
                System.out.println("|-_-_-_-_-_-_-_-_-_-_-_+_-_-_-_-_-_-_-_-_-_+_-_-_-_-_-_-_-_-_-_-_-_|");
            }
            case "opt" -> {
                System.out.println(STD_STR);
                System.out.println(fillString("Digite de 0-9 para opções!", " ", "|>", "<|"));
                System.out.println(fillString("", "-", "|", "|"));
            }
            case "invalid" -> {
                System.out.println(STD_STR);
                System.out.println(fillString("Opção Inválida! Tente novamente . . .", " ", "|", "|"));
                System.out.println(STD_STR);
            }
            case "div" -> System.out.println(fillString("", "-", "|", "|"));

            default -> System.out.println(fillString(str, " ", "|", "|"));
        }
    }

    private static String fillString(String str, String innerFill, String start, String end) {

        int offSet = STD_STR.length() - str.length() - start.length() - end.length();

        int repeatBefore = offSet / 2;
        int repeatAfter;

        if (offSet % 2 == 0) {
            repeatAfter = repeatBefore;
        } else {
            repeatAfter = repeatBefore + 1;
        }


        //Tratamento de Patern
        int restBefore = 0;
        int restAfter = 0;

        if (innerFill.length() > 1) {
            restBefore = repeatBefore % innerFill.length();
            restAfter = repeatAfter % innerFill.length();
            repeatBefore = repeatBefore / innerFill.length();
            repeatAfter = repeatAfter / innerFill.length();
        }

        String output = start;
        for (int i = 0; i < repeatBefore; i++) {
            output = output.concat(innerFill);
        }

        if (restBefore > 0) output = output + innerFill.substring(0, restBefore);

        output = output + str;

        for (int i = 0; i < repeatAfter; i++) {
            output = output.concat(innerFill);
        }

        if (restAfter > 0) output = output + innerFill.substring(0, restAfter);

        output = output + end;
        return output;
    }

    protected static boolean confirmPrompt(Scanner input, String msg) {
        while (true) {
            printer();
            printer(msg);
            printer("Confirma? S/N");
            printer();
            String confirm2 = input.nextLine();
            if (confirm2.equalsIgnoreCase("s")) return true;
            else if (confirm2.equalsIgnoreCase("n")) return false;
            printer();
            printer("Opção Inválida, digite S ou N");
        }
    }

    public static String formatDoubleToBRL(double balance) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("en_US1"));
        return "R$ " + formatter.format(balance)
                .replace(".", "?")
                .replace(",", ".")
                .replace("?", ",");
    }

    public static <T> void printer(List<T> list) {
        printer();
        printer("INICIO DA LISTAGEM");
        printer("div");
        for (T item : list) {
            if (item instanceof printerDetail) {
                for (String str : ((printerDetail) item).toStringDetail()) {
                    printer(str);
                }
            } else {
                printer(item.toString());
                printer("div");
            }
        }
        printer();
    }

    public static void printer(String[] str) {
        for (String item : str){
            printer(item);
        }
    }
}

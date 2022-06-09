package routines;

import java.util.Scanner;

import static routines.Utility.printer;

public abstract class dummy {
    public static void render(Scanner input){
        printer();
        printer("Error 404 - Ainda não implementado =(");
        printer("Aperte ENTER para continuar . . .");
        input.nextLine();
    }
}

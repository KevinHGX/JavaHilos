
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

	public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_CYAN = "\u001B[36m";

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int op;

		System.out.println(ANSI_RED+"\tSISTEMA DE VACIADO DE CONTENEDOR\n"+ANSI_WHITE);
		System.out.println("1.-Runnable");
		System.out.println("2.-Thread");
		System.out.print("Opcion >> ");

		op = input.nextInt();

		switch(op){
		case 1:
			System.out.println(ANSI_RED+"\t  SISTEMA RUNNABLE"+ANSI_WHITE);
			subMainBrazoRunnable A = new subMainBrazoRunnable();

			A.subMain();

			break;
		case 2:
			System.out.println(ANSI_RED+"\t  SISTEMA THREAD"+ANSI_WHITE);
			subMainBrazoThread B = new subMainBrazoThread();

			B.subMain();

			break;
		default:
			System.out.println("ERROR opcion invalida");
			break;
		}


	}

}
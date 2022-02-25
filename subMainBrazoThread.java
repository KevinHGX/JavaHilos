import java.util.ArrayList;
import java.util.Scanner;

public class subMainBrazoThread{

	public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";

	Scanner input = new Scanner(System.in);

	private ArrayList<BrazoThread> Brazo = new ArrayList<>();
	private ArrayList<Contenedor> Almacen = new ArrayList<>();

	private int deleteArm = -1;

	public int Menu() {

		int opcion;
		System.out.println(ANSI_RED+"\tSIMULADOR DE DESCARGAS\n"+ANSI_WHITE);
		System.out.println("1.-Definir Almacen");
		System.out.println("2.-Mostrar Almacen");
		System.out.println("3.-Modificar Almacen");
		System.out.println("4.-Asignar Brazos");
		System.out.println("5.-Brazos Establecidos");
		System.out.println("6.-Sistema de Descargas");
		System.out.println("7.-Salir");

		System.out.print("Opcion >> ");

		opcion = input.nextInt();

		return opcion;
	}

	public void subMain(){

		int op;

		do {
			// limpiarConsola();

			op = Menu();

			System.out.println("\n");

			switch (op) {
				case 1:
					System.out.println(ANSI_RED+"\tDATOS DE CONTENEDORES"+ANSI_WHITE);
					setAlmacen();

					break;
				case 2:
					System.out.println(ANSI_RED+"\tMOSTRAR ALMACEN"+ANSI_WHITE);
					mostrarAlmacen();

					break;
				case 3:
					System.out.println(ANSI_RED+"\tMODIFICAR DATOS DEL ALMACEN"+ANSI_WHITE);
					mostrarAlmacen();
					modificarAlmacen();

					break;
				case 4:
					System.out.println(ANSI_RED+"\tASIGNAR BRAZOS"+ANSI_WHITE);
					setBrazos();
					break;
				case 5:
					System.out.println(ANSI_RED+"\tBRAZOS CONFIGURADOS"+ANSI_WHITE);
					configuracionBrazos();

					break;
				case 6:
					System.out.println(ANSI_RED+"\tSISTEMA DE DESCARAGAS"+ANSI_WHITE);
					mostrarAlmacen();
					descargaDePiezas();

					break;
				case 7:
					System.out.println("Terminando...");
					break;
			}

			System.out.println("\n");

		} while (op != 7);

	}

	// -------------------------------SET ALMACEN-------------------------------
	//ALMACEN
	public void setAlmacen() {

		int Num_P;
		int index = idActualAlmacen();

		System.out.print("Total de piezas dentro del contenedor: ");
		Num_P = input.nextInt();

		while (Num_P != -1) {

			Contenedor contenedor = new Contenedor(index, Num_P);
			Almacen.add(contenedor);// agregamos nuevo contenedor;

			System.out.print("Total de piezas dentro del contenedor: ");
			Num_P = input.nextInt();

			index++;
		}
	}

	//ALMACEN
	public int idActualAlmacen() {

		if (Almacen.isEmpty()) {
			return 1;
		} else {
			return (Almacen.get(Almacen.size() - 1).getID()) + 1;
		}

	}

	// -----------------------------MOSTRAR ALMACEN-----------------------------

	public void borde() {
		System.out.println("+---------------------------+");
	}

	//ALMACEN
	public void mostrarAlmacen() {

		if (!Almacen.isEmpty()) {
			borde();
			System.out.println("| ID | CAPACIDAD/CONTENEDOR |");
			borde();
			for (int i = 0; i < Almacen.size(); i++) {
				Almacen.get(i).Mostrar();
				borde();
			}
		} else {
			System.out.println("El Alacen esta vacio");
		}
	}

	//-----------------------------MODIFICAR ALMACEN-----------------------------
	public void modificarAlmacen(){
		int index,capacidad;
		System.out.print("Selecciona el ID del Contenedor a Modificar: ");
		index = input.nextInt();
		index = index-1;
		System.out.print("Capacidad actual del Contenedor: "+Almacen.get(index).getCapacidad()+"\n");
		System.out.print("Ingresa nueva Capacidad del contenedor: ");
		capacidad = input.nextInt();

		Almacen.get(index).setCapacidad(capacidad);

		mostrarAlmacen();
	}
	// --------------------------------RUNNABLE--------------------------------
	//ALMACEN/BRAZO
	public void setBrazos() {
		int Num_T, id_contenedor;
		int index,op;

		do {
			mostrarAlmacen();

			System.out.println("\n");
			System.out.println("Configuracion de Brazo");
			System.out.print("ID: ");
			index = input.nextInt();
			System.out.print("Piezas a Extraer: ");
			Num_T = input.nextInt();
			System.out.print("ID de Contenedor: ");
			id_contenedor = input.nextInt();
			System.out.println("\n");
			System.out.print("Configurar nuevo Brazo (si = 1/no = 0): ");
			op = input.nextInt();
			System.out.print("--------------------------------------------------------");
			System.out.println("\n");

			BrazoThread New_Brazo = new BrazoThread(index, Num_T, Almacen.get(id_contenedor - 1));
			Brazo.add(New_Brazo);// agregamos nuevo contenedor;

		}while(op != 0);
	}

	// -----------------------------MOSTRAR BRAZOS-----------------------------

	public static void bordeB() {
		System.out.println("+---------------------------------+");
	}

	public int search(int target){

		int index = -1;
		for(int i = 0;i<Brazo.size();i++){
			if(Brazo.get(i).hilo.getID() == target){
				index = i;
				break;	
			}
		}

		return index;
	} 
	//ALMACEN/BRAZO
	public void modificarAtributos(int target) {

		int op, aux,index;
		System.out.println("ATRIBUTO A MODIFICAR");
		System.out.println("1.-ID");
		System.out.println("2.-Numero de piezas");
		System.out.println("3.-Nuevo contenedor");
		System.out.println("Opcion: ");

		op = input.nextInt();
		System.out.println("\n");
		index = search(target); 

		switch (op) {
			case 1:

				System.out.println("ID actual : " + Brazo.get(index).hilo.getID());

				System.out.print("Modificar ID: ");
				aux = input.nextInt();

				Brazo.get(index).hilo.setID(aux);

				break;
			case 2:

				System.out.println("N numero de Piezas Actuales: " + Brazo.get(index).hilo.getNum_P());

				System.out.print("Modificacion del numero de piezas: ");
				aux = input.nextInt();
				Brazo.get(index).hilo.setNum_P(aux);

				break;
			case 3:

				System.out.println("ID actual del Contenedor: " + Brazo.get(index).hilo.getIdContenedor());

				mostrarAlmacen();

				System.out.print("Nuevo Contenedor:");
				aux = input.nextInt();
				Brazo.get(index).hilo.setContenedor(Almacen.get(aux - 1));

				break;
		}
	}

	//------------------------------ELIMINARBRAZOS------------------------------
	public void DeleteArmsU(){

		int i = 0;

		while(i < Brazo.size()){
			if(Brazo.get(i).hilo.getIdContenedor() == deleteArm){
				Brazo.remove(i);
				if(Brazo.isEmpty()){ break; }
			}else{
				i++;
			}
		}
	}

	//-------------------------------ALMACEN/BRAZO-------------------------------
	public void configuracionBrazos() {

		if(deleteArm != -1){
			DeleteArmsU();
			deleteArm = -1;
		}

		if (!Brazo.isEmpty()) {

			bordeB();
			System.out.println("| ID | NUM//PIEZAS | ID_CONTENEDOR |");
			bordeB();
			for (int i = 0; i < Brazo.size(); i++) {
				Brazo.get(i).hilo.Mostrar();
				bordeB();
			}
			System.out.println("\n");
			// modificaciones
			int op, index;
			System.out.println("Realizar Modificaciones (si=1/no=0)");
			op = input.nextInt();

			if (op == 1) {
				System.out.println("ID del Brazos a Modififcar: ");
				index = input.nextInt();
				System.out.println("\n");
				modificarAtributos(index);
			}

		} else {
			System.out.println("Los Brazos aun no han sido inicializados");
		}

	}
	//-----------------------------DESACARGAR PIEZAS-----------------------------
	public void descargaDePiezas() {

		System.out.println("\n");

		int opcion;
		boolean flag = false;
		System.out.print("Seleccione el ID de Contenedor a vaciar: ");
		opcion = input.nextInt();
		deleteArm = opcion;
		if (Almacen.get(opcion-1).getCapacidad() == 0) {
			System.out.println("Contenedor con ID: " + opcion + " actualmente Vacio");
		} else {

			for (int i = 0; i < Brazo.size(); i++) {

				System.out.println("+----------------------------------+");
				

				if (Brazo.get(i).hilo.getIdContenedor() == opcion) {
					flag = true;
					Brazo.get(i).start();// hilos;
					//JOIN
					//try{
					//	Brazo.get(i).join();
						/*
						se utiliza para mantener la ejecución del hilo
						que se está ejecutando actualmente hasta que
						el hilo especificado esté muerto
						*/
					//}catch(InterruptedException e){}

				}
			}
			if (!flag) {
				System.out.println("El contenedor con el ID: " + opcion + " no tiene Brazos aun configurados");
			}
		}
	}



}
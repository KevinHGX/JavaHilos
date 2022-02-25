public class Contenedor{

	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE = "\u001B[37m";

	private int ID;
	private int capacidad;

	public Contenedor(int _id,int _capacidad){
		this.ID = _id;
		this.capacidad = _capacidad;
	}

	public void setCapacidad(int _capacidad){
		this.capacidad = _capacidad;
	}

	public int getID(){
		return this.ID;
	}

	public int getCapacidad(){
		return this.capacidad;
	}
	/*region critica
	synchronized- ejecucion por exclusion mutua,
	Solamente un subproceso puede acceder a dicho
	método a la vez, cada método sincronizado posee
	una especie de llave que puede cerrar o abrir la 
	puerta de acceso.
	*/
	synchronized public void descargarUnaPieza(){
		/*
	 	Todos los objetos java tienen el método wait() 
	 	que deja bloqueado al hilo que lo llama 
	 	y el método notify(), que desbloquea a los 
	 	hilos bloqueados por wait()
		*/
		if(capacidad == 0){
			while(capacidad == 0){
				try{
					System.out.println(ANSI_YELLOW+"--- Contenedor Completamente Vacio ---"+ANSI_WHITE);
					System.out.println(ANSI_YELLOW+" >> Hilo En espera..."+ANSI_WHITE);
					wait();
					System.out.println(ANSI_YELLOW+" >> Hilo Liberado <<"+ANSI_WHITE);
				}catch(InterruptedException e){
					System.out.println("Thread Interrupted"+e);
				}
			}
		}else{
			notifyAll();
			capacidad = capacidad-1;
		}
	}

	public void Mostrar(){
		System.out.print("| "+ID+"  |");
		System.out.println("         "+capacidad);
	}	

}

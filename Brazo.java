public class Brazo {

	private int ID;
	private int Num_P;
	private Contenedor contenedor;
	
	private boolean flag = false;

	Brazo(int _id, int _num_p, Contenedor _contenedor) {
		this.ID = _id;
		this.Num_P = _num_p;
		this.contenedor = _contenedor;
	}
	// ----SETTER------

	public void setID(int _id) {
		this.ID = _id;
	}

	public void setNum_P(int _num_p) {
		this.Num_P = _num_p;
	}

	public void setContenedor(Contenedor _contenedor) {
		this.contenedor = _contenedor;
	}

	// ----GETTER------
	public int getID() {
		return this.ID;
	}

	public int getNum_P() {
		return this.Num_P;
	}

	public int getIdContenedor() {
		return this.contenedor.getID();
	}

	public void Mostrar() {
		System.out.print("| " + ID + "  ");
		System.out.print("|      " + Num_P + "     ");
		System.out.println("|       " + contenedor.getID() + "        ");
	}

	// ---------------------
	
	public boolean descargar() {
		//preguntamos si el contenedor de encuentra vacio
		if(this.contenedor.getCapacidad() == 0){ flag = true; }

		this.contenedor.descargarUnaPieza();
		return flag;
	}

	public int capacidadActual() {
		return this.contenedor.getCapacidad();
	}

}
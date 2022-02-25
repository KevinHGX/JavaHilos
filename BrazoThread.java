
class BrazoThread extends Thread {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_CYAN = "\u001B[36m";

    Brazo hilo;

    BrazoThread(int _id,int _num_p,Contenedor _contenedor){
        this.hilo = new Brazo(_id,_num_p,_contenedor);    
    }

    @Override
    public void run(){
        for(int i=1;i<= hilo.getNum_P();i++){

            boolean pass = hilo.descargar();//extraccion de pieza del contenedor

            //terminamos la ejecucion si ya no hay piezas que extraer
            if(pass){ break; }

            System.out.println(ANSI_CYAN+"Brazo N "+(hilo.getID())+ANSI_WHITE+" descarga: "+ANSI_RED+i+ANSI_WHITE+" pieza");

            try{
                sleep(300);
            }catch(InterruptedException e){}
        }
    }

}
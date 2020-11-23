/*  PROYECTO FINAL DE PROGRAMACION CONCURRENTE
    FECHA: 22/11/2020
    EQUIPO: CASTILLO AVILA MARTI GALDINO, GARCIA CRUZ RICARDO EMMANUEL, MERLO SIMONI MARIA FERNANDA, SESEÑA ROBLES DIANA SARAHI, VARGAS ARENAS PEDRO
*/
public class Caja {
<<<<<<< HEAD
    
    private Heladeria heladeria;
    
    public Caja(){}
    
    //Cuando una persona paga, utiliza este método
    public void pagar(int persona, int grupo){
        
=======
    private Heladeria heladeria;
    
    public Caja(){

    }

    public void pagar(int persona, int grupo){
>>>>>>> 108a8dc0371765f8d59f6e6ff21e89e1a1e1b6bd
        try {
            System.err.print("****Region critica: ");
            System.err.println("El cliente " + persona + " del grupo "+grupo+ " comienza a pagar su helado");
            Thread.sleep(2000);// Tiempo para pagar
            System.err.print("****Region critica: ");
            System.err.println("El cliente " + persona + " del grupo "+grupo+ " terminó de pagar su helado");
        } catch (InterruptedException E) {
<<<<<<< HEAD
            System.err.println("Se generó una excepcion pidiendo helado");
        }
    }
    
    //Notifica que ele grupo terminó de pagar
=======
            System.err.println("Se genero una excepcion pidiendo helado");
        }
    }

>>>>>>> 108a8dc0371765f8d59f6e6ff21e89e1a1e1b6bd
    public void pagarGrupo (int grupo){
        System.out.println("El grupo "+ grupo+" ha terminado de pedir sus helados y comieza a pagar");
    }

    public void setHeladeria(Heladeria heladeria){
        this.heladeria = heladeria;
    }
}

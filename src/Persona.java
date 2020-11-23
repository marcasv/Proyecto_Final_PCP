/*  PROYECTO FINAL DE PROGRAMACION CONCURRENTE
    FECHA: 22/11/2020
    EQUIPO: CASTILLO AVILA MARTI GALDINO, GARCIA CRUZ RICARDO EMMANUEL, MERLO SIMONI MARIA FERNANDA, SESEÑA ROBLES DIANA SARAHI, VARGAS ARENAS PEDRO
*/
import java.util.concurrent.Semaphore;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Persona extends Thread {
    private int nombre;
    private String helado;
    private boolean pedido;
    private boolean pagado;
    private Mostrador mostrador;
    private Caja caja;
    private boolean grupoListo;
    private boolean grupoPedido;
    private int nombreGrupo;
    // Definir semaforo de caja y mostrador
    private static Semaphore mostradorPersona = new Semaphore(1);;
    // Semaforo de caja
    private static Semaphore cajaPersona = new Semaphore(1);
    private Grupo grupo;
    private Semaforo s;
    private Semaforo s1;

    public Persona(int nombre, Mostrador mostrador, Caja caja, int grupo, Semaforo s, Semaforo s1) {
        this.caja = caja;
        this.mostrador = mostrador;
        this.nombre = nombre;
        this.pedido = false;
        this.pagado = false;
        this.helado = "";
        nombreGrupo = grupo;
        grupoListo = false;
        grupoPedido = false;
        this.s = s;
        this.s1 = s1;
    }

    @Override
    public void run() {
                Random r = new Random();
                int num = r.nextInt(3);
                System.out.println("Persona " + nombre + " del grupo " + nombreGrupo + " eligiendo helado....");
                try {
                    Thread.sleep((num) * 1000);
                    helado = elegirHelado(mostrador.getSabores(), num);
                    System.out.println("Persona " + nombre + " del grupo " + nombreGrupo + " eligió helado: " + helado);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                //PEDIR HELADO
                try {
                    mostradorPersona.acquire();
                        mostrador.pedirHelado(nombre, helado, nombreGrupo);
                        pedido = true;
                    mostradorPersona.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }///
                
                while(!grupo.isGrupoPedido()){
                    
                }
                //s.release();
                grupo.desactivarSemaforo();
                
                
                //------------------------------------------------------------
                
                
                try {
                    grupo.solicitarSemaforoCaja();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    cajaPersona.acquire();
                        caja.pagar(nombre, nombreGrupo);
                        pagado = true;
                    cajaPersona.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                while(!grupo.isGrupoTerminado()){
                    
                }
                grupo.desactivarSemaforoCaja();
                
    }

    public String elegirHelado(String [] sabores,int num){
       
        return sabores[num];
    }

    public boolean isPagado(){
        return pagado;
    }

    public boolean isPedido(){
        return pedido;
    }

    public void setGrupoListo(boolean grupo){
        this.grupoListo = grupo;
    }
    public void setGrupoPedido(boolean grupo){
        this.grupoPedido = grupo;
    }


    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
}
/*  PROYECTO FINAL DE PROGRAMACION CONCURRENTE
    FECHA: 22/11/2020
    EQUIPO: CASTILLO AVILA MARTI GALDINO, GARCIA CRUZ RICARDO EMMANUEL, MERLO SIMONI MARIA FERNANDA, SESEÑA ROBLES DIANA SARAHI, VARGAS ARENAS PEDRO
*/
import java.util.concurrent.Semaphore;
import java.util.Random;

/*La clase Persona modela a los individuos que pertenecen a los grupos, como
estos también interactúan tanto con el mostrador como con la caja deben tener
objetos de este tipo, estos se obtienen de la clase Grupo al momento de que se
crean. También posee variables booleanas para verficar si la persona ha pedido
y ha pagado. Posee los semáforos que corresponden para mostrador y caja, estos
semáforos son para que una persona del grupo pueda utilizar el mostrador o la
caja a la vez*/
public class Persona extends Thread {
    
    private int nombre;
    private String helado;
    private boolean pedido;
    private boolean pagado;
    private Mostrador mostrador;
    private Caja caja;
    private int nombreGrupo;
    // Definir semaforo de caja y mostrador
    private static Semaphore mostradorPersona;
    // Semaforo de caja
    private static Semaphore cajaPersona;
    private Grupo grupo;

    public Persona(int nombre, Mostrador mostrador, Caja caja, int grupo) {
        this.caja = caja;
        this.mostrador = mostrador;
        this.nombre = nombre;
        this.pedido = false;
        this.pagado = false;
        this.helado = "";
        this.nombreGrupo = grupo;
        this.mostradorPersona = new Semaphore(1);
        this.cajaPersona = new Semaphore(1);
    }

    @Override
    public void run() {
        
        Random r = new Random();
        int num = r.nextInt(3);
        /*En esta parte la persona elige su helado, primero "piensa" en cuál
        helado quiere, para esto se asigna un tiempo aleatorio entre 0 y 3
        segundos y posteriormente, lo elige. Estos e guarda en la variable
        de tipo String helado.*/
        System.out.println("Persona " + nombre + " del grupo " +
                           nombreGrupo + " eligiendo helado....");
        try {
            Thread.sleep((num) * 1000);
            helado = elegirHelado(mostrador.getSabores(), num);
            System.out.println("Persona " + nombre + " del grupo " +
                               nombreGrupo + " eligió helado: " + helado);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        
        /*En esta parte la persona pide el helado previamente seleccionado.
        Activa el semáforo mostradorPersona para evitar que otra persona
        del mismo grupo utilice el mostrador, posteriormente pide el helado y 
        la variable pedido se le asigna true. Por último, desactiva el semáforo 
        mostradorPersona para que otra persona del mismo grupo pida*/
        try {
            mostradorPersona.acquire();
            mostrador.pedirHelado(nombre, helado, nombreGrupo);
            pedido = true;
            mostradorPersona.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }///
        
        /*Este ciclo es para que nadie desactive el semáforo de mostrador del
        grupo hasta que todos hayan pedido. Mientras, aquí las personas
        se ciclan*/
        while(!grupo.isGrupoPedido()){

        }
        //s.release();
        grupo.desactivarSemaforo();

        //------------------------------------------------------------
        /*En esta parte la persona paga el helado. Activa el semáforo
        de caja del grupo para que ningún otro grupo lo utilice a la par.
        Posteriormente, activa el semáforo cajaPersona para evitar que otra 
        persona del mismo grupo utilice la caja, posteriormente paga, 
        la variable pagado se le signa true y desactiva el semáforo cajaPersona
        para que otra persona del mismo grupo pague*/
        try {
            grupo.solicitarSemaforoCaja();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            cajaPersona.acquire();
            caja.pagar(nombre, nombreGrupo);
            pagado = true;
            cajaPersona.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*Ninguna persona puede desactivar el semáforo de caja del grupo hasta
        que todos hayan pagado. Todas las personas que pagaron se ciclan aquí*/
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

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
}
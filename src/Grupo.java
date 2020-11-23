/*  PROYECTO FINAL DE PROGRAMACION CONCURRENTE
    FECHA: 22/11/2020
    EQUIPO: CASTILLO AVILA MARTI GALDINO, GARCIA CRUZ RICARDO EMMANUEL, MERLO SIMONI MARIA FERNANDA, SESEÑA ROBLES DIANA SARAHI, VARGAS ARENAS PEDRO
*/
import java.util.logging.Level;
import java.util.logging.Logger;


public class Grupo extends Thread {
    
    private Persona [] integrantes;
    private Mostrador mostrador;
    private Caja caja;
    private boolean inicioPedido;
    private int nombre;
    
    private static Semaforo sem_mostrador = new Semaforo(1);
    private static Semaforo sem_caja = new Semaforo(1);
    
    private boolean tengoSemaforo = false;
    private boolean tengoSemaforoCaja = false;

    public Grupo(int numero, int nombre, Caja caja, Mostrador mostrador){
        this.mostrador = mostrador;
        this.caja = caja;
        this.integrantes = new Persona[numero];
        // this.helados = helados;
        this.nombre = nombre;
        inicioPedido = false;
        crearPersonas();
        setGrupo(this);
    }
    public void run(){
        //Correr todos las personas del grupo
        System.out.println("Grupo run");
        mostrador.pedirGrupo(nombre);
        try{
            sem_mostrador.acquire();
            tengoSemaforo = true;
            for(int i =0; i< integrantes.length;i++){
                integrantes[i].start();
            }
            //CON ESTO SE ESPERA A QUE TODAS LAS PERSONAS TERMINEN
            for(int i =0; i< integrantes.length;i++){
                integrantes[i].join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
        }    
        
        
        
        /*while (!isGrupoTerminado()){//Ya terminaron todos de pedir y de pagar 
            if(isGrupoPedido()){
                //Ya terminaron de pedir todos y comienzan a pagar
                //Regresar Mostrador
                //Pedir semafooro caja
                //sem_mostrador.release();
                try {
                    sem_caja.acquire();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
                }
                caja.pagarGrupo(nombre);
            }
        }
        //Regresar caja
        sem_caja.release();*/
        
        
        //Avisar a sus personas que ya terminaron todos
        System.out.println("El grupo "+ nombre+ " ha pagado y sale de la tienda.");
    }
    public void crearPersonas(){
        for(int i = 0; i < integrantes.length; i++)
            integrantes[i] = new Persona(i,mostrador, caja, nombre, sem_mostrador, sem_caja);
    }

    public boolean isGrupoTerminado(){
        boolean terminado = true;
        for(int i =0; i< integrantes.length;i++)
            terminado &= integrantes[i].isPagado();
        return terminado;
    }

    public boolean isGrupoPedido(){
        boolean terminado = true;
        for(int i =0; i< integrantes.length;i++)
            terminado &= integrantes[i].isPedido();
        return terminado;
    }

    public Persona [] personas(){
        return integrantes;
    }
    
    public void setGrupo(Grupo grupo){
        for(int i =0; i< integrantes.length;i++)
            integrantes[i].setGrupo(grupo);
    }

    public boolean TengoSemaforo() {
        return tengoSemaforo;
    }

    public synchronized void desactivarSemaforo(){
        if(tengoSemaforo){
            tengoSemaforo = false;
            sem_mostrador.release();
        }
    }
    
    public synchronized void desactivarSemaforoCaja(){
        if(tengoSemaforoCaja){
            tengoSemaforoCaja = false;
            sem_caja.release();
        }
    }
    
    public synchronized void solicitarSemaforoCaja() throws InterruptedException{
        if(!tengoSemaforoCaja){
            sem_caja.acquire();
            tengoSemaforoCaja = true;
        }
    }
}

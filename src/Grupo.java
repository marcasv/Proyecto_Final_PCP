/*  PROYECTO FINAL DE PROGRAMACION CONCURRENTE
    FECHA: 22/11/2020
    EQUIPO: CASTILLO AVILA MARTI GALDINO, GARCIA CRUZ RICARDO EMMANUEL, MERLO SIMONI MARIA FERNANDA, SESEÑA ROBLES DIANA SARAHI, VARGAS ARENAS PEDRO
*/
<<<<<<< HEAD

/*La clase grupo contiene un arreglo de tipo Persona que simulan ser
las personas que pertenecen al mismo. Contiene un objeto de tipo de Mostrador y
otro de tipo Caja, ya que primero interactúa con el mostrador y la caja.
Es por ello que también posee semáforos tanto para mostrador como para caja.
Al igual, tiene variables booleanas para saber si el grupo está usando
el mostrador o la caja, es decir, si los semáforos están activados.*/
=======
import java.util.logging.Level;
import java.util.logging.Logger;


>>>>>>> 108a8dc0371765f8d59f6e6ff21e89e1a1e1b6bd
public class Grupo extends Thread {
    
    private Persona [] integrantes;
    private Mostrador mostrador;
    private Caja caja;
<<<<<<< HEAD
    //private boolean inicioPedido;
    private int nombre;
    private static Semaforo sem_mostrador;
    private static Semaforo sem_caja;
=======
    private boolean inicioPedido;
    private int nombre;
    
    private static Semaforo sem_mostrador = new Semaforo(1);
    private static Semaforo sem_caja = new Semaforo(1);
    
>>>>>>> 108a8dc0371765f8d59f6e6ff21e89e1a1e1b6bd
    private boolean tengoSemaforo = false;
    private boolean tengoSemaforoCaja = false;

    public Grupo(int numero, int nombre, Caja caja, Mostrador mostrador){
        this.mostrador = mostrador;
        this.caja = caja;
        this.integrantes = new Persona[numero];
<<<<<<< HEAD
        this.nombre = nombre;
        //this.inicioPedido = false;
        this.sem_mostrador = new Semaforo(1);
        this.sem_caja = new Semaforo(1);
        //Crea los objetos de tipo Persona.
        crearPersonas();
        //Le pasa a todos los integrantes la referencia al grupo perteneciente
        setGrupo(this);
    }
    
    public void run(){
        
        System.out.println("Grupo run");
        /*El primer hilo de tipo Grupo que se ejecute será el primero que
        pida el mostrador y activará el semáforo de mostrador para que
        los demás grupos esperen.*/ 
=======
        // this.helados = helados;
        this.nombre = nombre;
        inicioPedido = false;
        crearPersonas();
        setGrupo(this);
    }
    public void run(){
        //Correr todos las personas del grupo
        System.out.println("Grupo run");
>>>>>>> 108a8dc0371765f8d59f6e6ff21e89e1a1e1b6bd
        mostrador.pedirGrupo(nombre);
        try{
            sem_mostrador.acquire();
            tengoSemaforo = true;
<<<<<<< HEAD
            //Ejecuta a las personas para que comiencen a ser atendidas
            for(int i =0; i< integrantes.length;i++)
                integrantes[i].start();
            //CON ESTO SE ESPERA A QUE TODAS LAS PERSONAS TERMINEN
            for(int i =0; i< integrantes.length;i++)
                integrantes[i].join();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }    
        //Mensaje para avisar a sus personas que ya terminaron todos
        System.out.println("El grupo "+ nombre+ " ha pagado y sale de la tienda.");
    }
    
    //Crea los objetos de tipo Persona
    public void crearPersonas(){
        for(int i = 0; i < integrantes.length; i++)
            integrantes[i] = new Persona(i,mostrador, caja, nombre);
    }
    
    //Verifica si todos los integrantes del grupo ya pagaron. Esto es lo
    //último que realizan
    public boolean isGrupoTerminado(){
        
        boolean terminado = true;
        
        for(int i =0; i< integrantes.length;i++)
            terminado &= integrantes[i].isPagado();
        
        return terminado;
    }
    
    //Verifica si todos los integrantes ya pidieron y por ende, tienen su helado
    public boolean isGrupoPedido(){
        
        boolean terminado = true;
        
        for(int i =0; i< integrantes.length;i++)
            terminado &= integrantes[i].isPedido();
        
        return terminado;
    }
    
    //Le envía la referencia del grupo a todos los integrantes
    public void setGrupo(Grupo grupo){
    
        for(int i =0; i< integrantes.length;i++)
            integrantes[i].setGrupo(grupo);
    }
    
    /*Solo una persona puede desactivar el semáforo del mostrador que se activó
    en el método run. Es por ello que este método debe estar sincronizado*/
    public synchronized void desactivarSemaforo(){
        //Antes de desactivar debe de revisar si el semáforo mostrador
        //del grupo está activado
=======
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
>>>>>>> 108a8dc0371765f8d59f6e6ff21e89e1a1e1b6bd
        if(tengoSemaforo){
            tengoSemaforo = false;
            sem_mostrador.release();
        }
    }
    
<<<<<<< HEAD
    /*Solo una persona puede desactivar el semáforo de la caja como grupo
    que se activó en la clase Persona en el método run. Es por ello que este
    método debe estar sincronizado*/
    public synchronized void desactivarSemaforoCaja(){
        //Antes de desactivar debe de revisar si el semáforo caja 
        //del grupo está activado
=======
    public synchronized void desactivarSemaforoCaja(){
>>>>>>> 108a8dc0371765f8d59f6e6ff21e89e1a1e1b6bd
        if(tengoSemaforoCaja){
            tengoSemaforoCaja = false;
            sem_caja.release();
        }
    }
    
<<<<<<< HEAD
    /*Activa el semáforo como grupo, de igual manera, solo una persona debe
    activarlo, es por ello que está sincronizado. Este método se llama en
    la clase Persona*/
    public synchronized void solicitarSemaforoCaja() throws InterruptedException{
        
        //Antes de activarlo debe revisar si no está activado
=======
    public synchronized void solicitarSemaforoCaja() throws InterruptedException{
>>>>>>> 108a8dc0371765f8d59f6e6ff21e89e1a1e1b6bd
        if(!tengoSemaforoCaja){
            sem_caja.acquire();
            tengoSemaforoCaja = true;
        }
    }
<<<<<<< HEAD
    
=======
>>>>>>> 108a8dc0371765f8d59f6e6ff21e89e1a1e1b6bd
}

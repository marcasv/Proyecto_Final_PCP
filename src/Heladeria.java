/*  PROYECTO FINAL DE PROGRAMACION CONCURRENTE
    FECHA: 22/11/2020
    EQUIPO: CASTILLO AVILA MARTI GALDINO, GARCIA CRUZ RICARDO EMMANUEL, MERLO SIMONI MARIA FERNANDA, SESEÑA ROBLES DIANA SARAHI, VARGAS ARENAS PEDRO
*/
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class Heladeria{
    private Mostrador mostrador;
    private Caja caja;
    private int barquillos;
    private int servilletas;
    private Hashtable<String, Integer> sabores;

    public Heladeria (Mostrador mostrador, Caja caja){
        this.mostrador = mostrador;
        this.caja = caja;
        this.barquillos = 10;
        this.servilletas = 10;
        this.sabores  = new Hashtable<String, Integer>();
    }
    public void abrirHeladeria(){
        iniciarSabores();
        System.out.println("SE ABRE LA HELADERIA");
    }
    public Hashtable<String, Integer> getNumSabores(){
        return sabores;
    }

    private void iniciarSabores(){
        sabores.put("Chocolate", 10);
        sabores.put("Vainilla", 10);
        sabores.put("Fresa", 10);
    }
    
    public void restarHelado(String helado){
        
        sabores.replace(helado, sabores.get(helado), sabores.get(helado) - 1);
        barquillos -=1;
        servilletas -=1;
    }
    
    public void llenarSabor(String helado){
        
        sabores.replace(helado, 0, 10);
    }
    
    public boolean verificarSabor(String helado){
        if(sabores.get(helado) == 0)
            return false;
        else
            return true;
    }
    
    public boolean verificarServilletas(){
        
         if(servilletas == 0)
           return false;
         else{
             servilletas--;
             return true;
         }
    }
    
    public boolean verificarBarquillos(){
        
        if(barquillos == 0)
           return false;
         else{
             barquillos--;
             return true;
         }
    }
    
    public void llenarServilletas(){
        
        this.servilletas = 10;
    }
    
    public void llenarBarquillos(){
        
        this.barquillos = 10;
    }
    
    public String [] getSabores(){
        String [] sab = {"Chocolate", "Vainilla", "Fresa"};
        return sab;
    }
    public void bloquearCaja(){
        // caja.bloquear();
    }
    public static void main(String[] args) {
        Mostrador mostrador = new Mostrador();
        Caja caja  = new Caja();
        Heladeria heladeria  = new Heladeria(mostrador,caja);
        mostrador.setHeladeria(heladeria);
        caja.setHeladeria(heladeria);
        heladeria.abrirHeladeria();

        /*Queue<Grupo> colaGrupo;
        colaGrupo = new LinkedList();
        colaGrupo.add(new Grupo(5, 1, caja, mostrador));
        colaGrupo.add(new Grupo(3, 2, caja, mostrador));
        // colaGrupo.add(new Grupo(3, 3, caja, mostrador));

        for(int i = 0; i < colaGrupo.size(); i++){
            colaGrupo.poll().run();
        }*/
        
        Grupo g1 = new Grupo(5, 1, caja, mostrador);
        Grupo g2 = new Grupo(3, 2, caja, mostrador);
        Grupo g3 = new Grupo(3, 3, caja, mostrador);
        Grupo g4 = new Grupo(6, 4, caja, mostrador);
        Grupo g5 = new Grupo(10, 5, caja, mostrador);
        g1.start();
        g2.start();
        g3.start();
        g4.start();
        g5.start();
    }
}

